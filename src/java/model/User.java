package model;

public class User {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private int roleId;
    private String roleName;

    public User() {}
    public User(int id, String email, String password, String fullName,
                String address, String phone, int roleId, String roleName) {
        this.id=id; this.email=email; this.password=password; this.fullName=fullName;
        this.address=address; this.phone=phone; this.roleId=roleId; this.roleName=roleName;
    }
    public int getId(){return id;} public void setId(int v){id=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public int getRoleId(){return roleId;} public void setRoleId(int v){roleId=v;}
    public String getRoleName(){return roleName;} public void setRoleName(String v){roleName=v;}
}
