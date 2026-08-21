package dao;

import model.User;
import model.Role;
import utils.DBConnection;
import java.sql.*;
import java.util.*;

public class UserDAO {
    public User login(String email, String password) throws SQLException {
        String sql="SELECT u.*, r.name role_name FROM users u JOIN roles r ON u.role_id=r.id WHERE u.email=? AND u.password=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,email); p.setString(2,password);
            try(ResultSet rs=p.executeQuery()){ if(rs.next()) return map(rs); }
        } return null;
    }
    public List<User> findAll() throws SQLException {
        List<User> list=new ArrayList<>();
        String sql="SELECT u.*,r.name role_name FROM users u JOIN roles r ON u.role_id=r.id ORDER BY u.id DESC";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql); ResultSet rs=p.executeQuery()){
            while(rs.next()) list.add(map(rs));
        } return list;
    }
    public List<User> findMembers() throws SQLException {
        List<User> list=new ArrayList<>();
        String sql="SELECT u.*,r.name role_name FROM users u JOIN roles r ON u.role_id=r.id WHERE r.name='MEMBER' ORDER BY u.full_name";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql); ResultSet rs=p.executeQuery()){
            while(rs.next()) list.add(map(rs));
        } return list;
    }
    public User findById(int id) throws SQLException {
        String sql="SELECT u.*,r.name role_name FROM users u JOIN roles r ON u.role_id=r.id WHERE u.id=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,id); try(ResultSet rs=p.executeQuery()){if(rs.next()) return map(rs);}
        } return null;
    }
    public void insert(User u) throws SQLException {
        String sql="INSERT INTO users(email,password,full_name,address,phone,role_id) VALUES(?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            set(p,u,false); p.executeUpdate();
        }
    }
    public void update(User u) throws SQLException {
        String sql="UPDATE users SET email=?,full_name=?,address=?,phone=?,role_id=? WHERE id=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,u.getEmail()); p.setString(2,u.getFullName()); p.setString(3,u.getAddress());
            p.setString(4,u.getPhone()); p.setInt(5,u.getRoleId()); p.setInt(6,u.getId()); p.executeUpdate();
        }
    }
    public void updatePassword(int id,String password) throws SQLException {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("UPDATE users SET password=? WHERE id=?")){
            p.setString(1,password); p.setInt(2,id); p.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("DELETE FROM users WHERE id=?")){
            p.setInt(1,id); p.executeUpdate();
        }
    }
    public List<Role> roles() throws SQLException {
        List<Role> list=new ArrayList<>();
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT * FROM roles ORDER BY id"); ResultSet rs=p.executeQuery()){
            while(rs.next()) list.add(new Role(rs.getInt("id"),rs.getString("name"),rs.getString("description")));
        } return list;
    }
    private void set(PreparedStatement p,User u,boolean withId)throws SQLException{
        p.setString(1,u.getEmail());p.setString(2,u.getPassword());p.setString(3,u.getFullName());
        p.setString(4,u.getAddress());p.setString(5,u.getPhone());p.setInt(6,u.getRoleId());
    }
    private User map(ResultSet r)throws SQLException{
        return new User(r.getInt("id"),r.getString("email"),r.getString("password"),r.getString("full_name"),
                r.getString("address"),r.getString("phone"),r.getInt("role_id"),r.getString("role_name"));
    }
}
