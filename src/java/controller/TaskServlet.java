package controller;
import dao.ProjectDAO;
import dao.TaskDAO;
import dao.UserDAO;
import model.Task;
import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/leader/tasks")
public class TaskServlet extends HttpServlet {
 private TaskDAO dao=new TaskDAO();
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{
   if("delete".equals(req.getParameter("action"))){dao.delete(Integer.parseInt(req.getParameter("id")));resp.sendRedirect("tasks");return;}
   req.setAttribute("tasks",dao.findAll());req.setAttribute("users",new UserDAO().findMembers());req.setAttribute("projects",new ProjectDAO().findAll());
   req.getRequestDispatcher("/leader/tasks.jsp").forward(req,resp);
  }catch(Exception e){throw new ServletException(e);}
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{
   Task t=new Task();t.setName(req.getParameter("name"));t.setDescription(req.getParameter("description"));t.setStartDate(req.getParameter("startDate"));t.setEndDate(req.getParameter("endDate"));t.setAssigneeId(Integer.parseInt(req.getParameter("assigneeId")));t.setProjectId(Integer.parseInt(req.getParameter("projectId")));t.setStatus(req.getParameter("status"));
   if(req.getParameter("id")==null||req.getParameter("id").isEmpty())dao.insert(t);else{t.setId(Integer.parseInt(req.getParameter("id")));dao.update(t);}
   resp.sendRedirect("tasks");
  }catch(Exception e){throw new ServletException(e);}
 }
}
