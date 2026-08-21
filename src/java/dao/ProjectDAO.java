package dao;

import model.Project;
import utils.DBConnection;
import java.sql.*;
import java.util.*;

public class ProjectDAO {
    public List<Project> findAll() throws SQLException { return find("SELECT p.*,u.full_name creator_name FROM projects p JOIN users u ON p.creator_id=u.id ORDER BY p.id DESC"); }
    public List<Project> findByCreator(int id) throws SQLException { return find("SELECT p.*,u.full_name creator_name FROM projects p JOIN users u ON p.creator_id=u.id WHERE p.creator_id="+id+" ORDER BY p.id DESC"); }
    public Project findById(int id) throws SQLException {
        String sql="SELECT p.*,u.full_name creator_name FROM projects p JOIN users u ON p.creator_id=u.id WHERE p.id=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,id);try(ResultSet r=p.executeQuery()){if(r.next())return map(r);}}return null;
    }
    public void insert(Project x)throws SQLException{
        String sql="INSERT INTO projects(name,description,start_date,end_date,creator_id) VALUES(?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){set(p,x);p.executeUpdate();}
    }
    public void update(Project x)throws SQLException{
        String sql="UPDATE projects SET name=?,description=?,start_date=?,end_date=? WHERE id=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,x.getName());p.setString(2,x.getDescription());p.setString(3,x.getStartDate());p.setString(4,x.getEndDate());p.setInt(5,x.getId());p.executeUpdate();
        }
    }
    public void delete(int id)throws SQLException{try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM projects WHERE id=?")){p.setInt(1,id);p.executeUpdate();}}
    public void addMember(int projectId,int userId)throws SQLException{
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("INSERT IGNORE INTO project_members(project_id,user_id) VALUES(?,?)")){p.setInt(1,projectId);p.setInt(2,userId);p.executeUpdate();}
    }
    public void removeMember(int projectId,int userId)throws SQLException{
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM project_members WHERE project_id=? AND user_id=?")){p.setInt(1,projectId);p.setInt(2,userId);p.executeUpdate();}
    }
    public List<model.User> members(int projectId)throws SQLException{
        List<model.User> l=new ArrayList<>();String sql="SELECT u.*,r.name role_name FROM project_members pm JOIN users u ON pm.user_id=u.id JOIN roles r ON u.role_id=r.id WHERE pm.project_id=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,projectId);try(ResultSet r=p.executeQuery()){while(r.next())l.add(new UserDAO().findById(r.getInt("id")));}}return l;
    }
    private List<Project> find(String sql)throws SQLException{List<Project>l=new ArrayList<>();try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){while(r.next())l.add(map(r));}return l;}
    private void set(PreparedStatement p,Project x)throws SQLException{p.setString(1,x.getName());p.setString(2,x.getDescription());p.setString(3,x.getStartDate());p.setString(4,x.getEndDate());p.setInt(5,x.getCreatorId());}
    private Project map(ResultSet r)throws SQLException{Project x=new Project();x.setId(r.getInt("id"));x.setName(r.getString("name"));x.setDescription(r.getString("description"));x.setStartDate(r.getString("start_date"));x.setEndDate(r.getString("end_date"));x.setCreatorId(r.getInt("creator_id"));x.setCreatorName(r.getString("creator_name"));return x;}
}
