<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Quản lý người dùng</title><style>
body{font-family:Arial;background:#f8fafc;padding:20px}table{width:100%;border-collapse:collapse;background:white}th,td{padding:10px;border:1px solid #ddd}form{background:white;padding:15px;margin-bottom:20px}input,select{padding:8px;margin:5px}button{padding:8px 15px}
</style></head><body><h2>Quản lý thành viên</h2><a href="${pageContext.request.contextPath}/admin/home">Trang chủ</a>
<form method="post" action="${pageContext.request.contextPath}/admin/users"><input type="hidden" name="action" value="add"><input name="email" placeholder="Email" required><input name="password" placeholder="Mật khẩu" required><input name="fullName" placeholder="Họ tên" required><input name="address" placeholder="Địa chỉ"><input name="phone" placeholder="Điện thoại"><select name="roleId"><% for(Role r:(List<Role>)request.getAttribute("roles")){ %><option value="<%=r.getId()%>"><%=r.getName()%></option><%}%></select><button>Thêm</button></form>
<table><tr><th>ID</th><th>Email</th><th>Họ tên</th><th>Quyền</th><th>Thao tác</th></tr>
<% for(User u:(List<User>)request.getAttribute("users")){ %><tr><td><%=u.getId()%></td><td><%=u.getEmail()%></td><td><%=u.getFullName()%></td><td><%=u.getRoleName()%></td><td><a href="?action=delete&id=<%=u.getId()%>" onclick="return confirm('Xóa user?')">Xóa</a></td></tr><%}%></table></body></html>
