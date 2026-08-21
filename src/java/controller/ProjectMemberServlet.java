package controller;
import dao.ProjectDAO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/leader/project-members")
public class ProjectMemberServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{
   ProjectDAO d=new ProjectDAO();int pid=Integer.parseInt(req.getParameter("projectId"));int uid=Integer.parseInt(req.getParameter("userId"));
   if("remove".equals(req.getParameter("action")))d.removeMember(pid,uid);else d.addMember(pid,uid);
   resp.sendRedirect("projects");
  }catch(Exception e){throw new ServletException(e);}
 }
}
