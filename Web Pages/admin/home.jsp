<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="java.util.*"%>
<% User u=(User)session.getAttribute("currentUser"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Admin</title><style>
body{font-family:Arial;margin:0;background:#f8fafc}.nav{background:#111827;color:white;padding:16px}.nav a{color:white;margin-right:20px;text-decoration:none}.box{padding:25px}.card{display:inline-block;background:white;padding:20px;margin:10px;border-radius:10px;box-shadow:0 2px 8px #ddd}
</style></head><body><div class="nav">CRM ADMIN | Xin chào <%=u.getFullName()%> <a href="${pageContext.request.contextPath}/admin/users">Người dùng</a><a href="${pageContext.request.contextPath}/leader/projects">Dự án</a><a href="${pageContext.request.contextPath}/leader/tasks">Công việc</a><a href="${pageContext.request.contextPath}/profile">Tài khoản</a><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></div><div class="box"><h2>Tổng quan</h2><div class="card">Dự án: ${projects.size()}</div><div class="card">Công việc: ${tasks.size()}</div></div></body></html>
