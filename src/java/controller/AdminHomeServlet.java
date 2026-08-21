package controller;
import dao.ProjectDAO;
import dao.TaskDAO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/home")
public class AdminHomeServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{req.setAttribute("projects",new ProjectDAO().findAll());req.setAttribute("tasks",new TaskDAO().findAll());req.getRequestDispatcher("/admin/home.jsp").forward(req,resp);}
  catch(Exception e){throw new ServletException(e);}
 }
}
