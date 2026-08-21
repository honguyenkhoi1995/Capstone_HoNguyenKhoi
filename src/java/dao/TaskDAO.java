package dao;

import model.Task;
import utils.DBConnection;
import java.sql.*;
import java.util.*;

public class TaskDAO {
    private String base="SELECT t.*,u.full_name assignee_name,p.name project_name FROM tasks t JOIN users u ON t.assignee_id=u.id JOIN projects p ON t.project_id=p.id ";
    public List<Task> findAll()throws SQLException{return find(base+"ORDER BY t.id DESC");}
    public List<Task> findByMember(int uid)throws SQLException{return find(base+"WHERE t.assignee_id="+uid+" ORDER BY t.id DESC");}
    public List<Task> findByProject(int pid)throws SQLException{return find(base+"WHERE t.project_id="+pid+" ORDER BY t.id DESC");}
    public void insert(Task t)throws SQLException{
        String sql="INSERT INTO tasks(name,description,start_date,end_date,assignee_id,project_id,status) VALUES(?,?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){set(p,t);p.executeUpdate();}
    }
    public void update(Task t)throws SQLException{
        String sql="UPDATE tasks SET name=?,description=?,start_date=?,end_date=?,assignee_id=?,project_id=?,status=? WHERE id=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,t.getName());p.setString(2,t.getDescription());p.setString(3,t.getStartDate());p.setString(4,t.getEndDate());p.setInt(5,t.getAssigneeId());p.setInt(6,t.getProjectId());p.setString(7,t.getStatus());p.setInt(8,t.getId());p.executeUpdate();}
    }
    public void updateStatus(int id,int memberId,String status)throws SQLException{
        String sql="UPDATE tasks SET status=? WHERE id=? AND assignee_id=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,status);p.setInt(2,id);p.setInt(3,memberId);p.executeUpdate();}
    }
    public void delete(int id)throws SQLException{try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM tasks WHERE id=?")){p.setInt(1,id);p.executeUpdate();}}
    public Map<String,Integer> statsByMember(int memberId)throws SQLException{
        Map<String,Integer> m=new LinkedHashMap<>();String sql="SELECT status,COUNT(*) c FROM tasks WHERE assignee_id=? GROUP BY status";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,memberId);try(ResultSet r=p.executeQuery()){while(r.next())m.put(r.getString("status"),r.getInt("c"));}}return m;
    }
    public Map<String,Integer> statsByProject(int projectId)throws SQLException{
        Map<String,Integer> m=new LinkedHashMap<>();String sql="SELECT status,COUNT(*) c FROM tasks WHERE project_id=? GROUP BY status";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,projectId);try(ResultSet r=p.executeQuery()){while(r.next())m.put(r.getString("status"),r.getInt("c"));}}return m;
    }
    private List<Task> find(String sql)throws SQLException{List<Task>l=new ArrayList<>();try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){while(r.next())l.add(map(r));}return l;}
    private void set(PreparedStatement p,Task t)throws SQLException{p.setString(1,t.getName());p.setString(2,t.getDescription());p.setString(3,t.getStartDate());p.setString(4,t.getEndDate());p.setInt(5,t.getAssigneeId());p.setInt(6,t.getProjectId());p.setString(7,t.getStatus());}
    private Task map(ResultSet r)throws SQLException{Task t=new Task();t.setId(r.getInt("id"));t.setName(r.getString("name"));t.setDescription(r.getString("description"));t.setStartDate(r.getString("start_date"));t.setEndDate(r.getString("end_date"));t.setAssigneeId(r.getInt("assignee_id"));t.setProjectId(r.getInt("project_id"));t.setStatus(r.getString("status"));t.setAssigneeName(r.getString("assignee_name"));t.setProjectName(r.getString("project_name"));return t;}
}
