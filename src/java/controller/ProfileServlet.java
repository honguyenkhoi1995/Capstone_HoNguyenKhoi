package controller;
import dao.UserDAO;
import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.getRequestDispatcher("/profile.jsp").forward(req,resp);}
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{User u=(User)req.getSession().getAttribute("currentUser");u.setEmail(req.getParameter("email"));u.setFullName(req.getParameter("fullName"));u.setAddress(req.getParameter("address"));u.setPhone(req.getParameter("phone"));new UserDAO().update(u);if(req.getParameter("password")!=null&&!req.getParameter("password").isEmpty())new UserDAO().updatePassword(u.getId(),req.getParameter("password"));req.getSession().setAttribute("currentUser",new UserDAO().findById(u.getId()));req.setAttribute("message","Cập nhật tài khoản thành công.");req.getRequestDispatcher("/profile.jsp").forward(req,resp);}
  catch(Exception e){throw new ServletException(e);}
 }
}
