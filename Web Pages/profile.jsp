<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<% User u=(User)session.getAttribute("currentUser"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Tài khoản</title><style>body{font-family:Arial;background:#f8fafc}.box{width:500px;margin:40px auto;background:white;padding:25px}input,button{width:100%;box-sizing:border-box;padding:10px;margin:7px 0}</style></head><body>
<div class="box"><h2>Quản lý tài khoản cá nhân</h2><%if(request.getAttribute("message")!=null){%><p style="color:green"><%=request.getAttribute("message")%></p><%}%>
<form method="post"><input name="email" value="<%=u.getEmail()%>" required><input name="fullName" value="<%=u.getFullName()%>" required><input name="address" value="<%=u.getAddress()==null?"":u.getAddress()%>"><input name="phone" value="<%=u.getPhone()==null?"":u.getPhone()%>"><input type="password" name="password" placeholder="Mật khẩu mới (để trống nếu không đổi)"><button>Cập nhật</button></form>
<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></div></body></html>
