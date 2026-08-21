package controller;
import dao.ProjectDAO;
import dao.UserDAO;
import model.Project;
import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/leader/projects")
public class ProjectServlet extends HttpServlet {
 private ProjectDAO dao=new ProjectDAO(); private UserDAO userDAO=new UserDAO();
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{
   User u=(User)req.getSession().getAttribute("currentUser");
   String action=req.getParameter("action");
   if("delete".equals(action)){dao.delete(Integer.parseInt(req.getParameter("id")));resp.sendRedirect("projects");return;}
   req.setAttribute("projects", "LEADER".equals(u.getRoleName())?dao.findByCreator(u.getId()):dao.findAll());
   req.setAttribute("members",userDAO.findMembers());
   req.getRequestDispatcher("/leader/projects.jsp").forward(req,resp);
  }catch(Exception e){throw new ServletException(e);}
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{
   User u=(User)req.getSession().getAttribute("currentUser");
   Project p=new Project();p.setName(req.getParameter("name"));p.setDescription(req.getParameter("description"));p.setStartDate(req.getParameter("startDate"));p.setEndDate(req.getParameter("endDate"));p.setCreatorId(u.getId());
   if(req.getParameter("id")==null||req.getParameter("id").isEmpty())dao.insert(p);else{p.setId(Integer.parseInt(req.getParameter("id")));dao.update(p);}
   resp.sendRedirect("projects");
  }catch(Exception e){throw new ServletException(e);}
 }
}
