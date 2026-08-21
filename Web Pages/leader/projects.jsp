<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%><%@page import="model.*"%>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Dự án</title><style>body{font-family:Arial;padding:20px}table{border-collapse:collapse;width:100%}td,th{border:1px solid #ddd;padding:8px}form{padding:12px;background:#f1f5f9;margin:10px 0}</style></head><body>
<h2>Quản lý dự án</h2><a href="${pageContext.request.contextPath}/leader/home">Leader Home</a>
<form method="post"><input name="name" placeholder="Tên dự án" required><input name="startDate" type="date"><input name="endDate" type="date"><input name="description" placeholder="Mô tả"><button>Thêm dự án</button></form>
<table><tr><th>ID</th><th>Tên</th><th>Mô tả</th><th>Bắt đầu</th><th>Kết thúc</th><th>Người tạo</th><th>Xóa</th></tr>
<% for(Project p:(List<Project>)request.getAttribute("projects")){ %><tr><td><%=p.getId()%></td><td><%=p.getName()%></td><td><%=p.getDescription()%></td><td><%=p.getStartDate()%></td><td><%=p.getEndDate()%></td><td><%=p.getCreatorName()%></td><td><a href="?action=delete&id=<%=p.getId()%>" onclick="return confirm('Xóa?')">Xóa</a></td></tr><%}%></table>
</body></html>
