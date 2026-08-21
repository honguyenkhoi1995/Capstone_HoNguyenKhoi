<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>CRM - Đăng nhập</title>
<style>
body{font-family:Arial;background:#f1f5f9}.box{width:380px;margin:100px auto;background:white;padding:30px;border-radius:12px;box-shadow:0 5px 20px #bbb}h2{text-align:center}input,button{width:100%;box-sizing:border-box;padding:11px;margin:8px 0}button{background:#2563eb;color:white;border:0;border-radius:6px}.err{color:#dc2626;text-align:center}
</style></head><body>
<div class="box"><h2>CRM - Đăng nhập</h2>
<% if(request.getAttribute("error")!=null){ %><div class="err"><%=request.getAttribute("error")%></div><%}%>
<form method="post" action="${pageContext.request.contextPath}/login">
<label>Email</label><input type="email" name="email" required>
<label>Mật khẩu</label><input type="password" name="password" required>
<button>Đăng nhập</button>
</form>
<p>Demo: admin@gmail.com / 123</p></div></body></html>
