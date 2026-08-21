<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%><% User u=(User)session.getAttribute("currentUser"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Leader</title></head><body style="font-family:Arial">
<h2>Leader: <%=u.getFullName()%></h2>
<p><a href="${pageContext.request.contextPath}/leader/projects">Quản lý dự án</a></p>
<p><a href="${pageContext.request.contextPath}/leader/tasks">Phân công công việc</a></p>
<p><a href="${pageContext.request.contextPath}/profile">Tài khoản cá nhân</a></p>
<p><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></p>
</body></html>
