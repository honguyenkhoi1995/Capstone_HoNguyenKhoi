package controller;
import dao.TaskDAO;
import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/member/tasks")
public class MemberTaskServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{User u=(User)req.getSession().getAttribute("currentUser");new TaskDAO().updateStatus(Integer.parseInt(req.getParameter("id")),u.getId(),req.getParameter("status"));resp.sendRedirect("home");}
  catch(Exception e){throw new ServletException(e);}
 }
}
