<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%><%@page import="model.*"%>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Member</title><style>body{font-family:Arial;padding:20px}table{border-collapse:collapse;width:100%}td,th{border:1px solid #ddd;padding:8px}</style></head><body>
<% User u=(User)session.getAttribute("currentUser"); %><h2>Xin chào <%=u.getFullName()%></h2>
<h3>Thống kê công việc cá nhân</h3>
<% Map<String,Integer> stats=(Map<String,Integer>)request.getAttribute("stats"); for(Map.Entry<String,Integer> e:stats.entrySet()){%><span style="display:inline-block;padding:12px;margin:5px;background:#eef"><%=e.getKey()%>: <%=e.getValue()%></span><%}%>
<h3>Công việc được giao</h3><table><tr><th>Tên</th><th>Dự án</th><th>Trạng thái</th><th>Cập nhật</th></tr>
<%for(Task t:(List<Task>)request.getAttribute("tasks")){%><tr><td><%=t.getName()%></td><td><%=t.getProjectName()%></td><td><%=t.getStatus()%></td><td><form method="post" action="${pageContext.request.contextPath}/member/tasks"><input type="hidden" name="id" value="<%=t.getId()%>"><select name="status"><option value="NOT_STARTED">Chưa bắt đầu</option><option value="IN_PROGRESS">Đang thực hiện</option><option value="COMPLETED">Đã hoàn thành</option></select><button>Cập nhật</button></form></td></tr><%}%></table>
<p><a href="${pageContext.request.contextPath}/profile">Tài khoản</a> | <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></p>
</body></html>
