package controller;
import dao.TaskDAO;
import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/member/home")
public class MemberHomeServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{User u=(User)req.getSession().getAttribute("currentUser");req.setAttribute("tasks",new TaskDAO().findByMember(u.getId()));req.setAttribute("stats",new TaskDAO().statsByMember(u.getId()));req.getRequestDispatcher("/member/home.jsp").forward(req,resp);}
  catch(Exception e){throw new ServletException(e);}
 }
}
