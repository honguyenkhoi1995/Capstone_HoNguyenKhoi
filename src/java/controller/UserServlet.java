package controller;

import dao.UserDAO;
import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/users")
public class UserServlet extends HttpServlet {
    private UserDAO dao=new UserDAO();
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            String action=req.getParameter("action");
            if("delete".equals(action)){dao.delete(Integer.parseInt(req.getParameter("id")));resp.sendRedirect("users");return;}
            req.setAttribute("users",dao.findAll());req.setAttribute("roles",dao.roles());
            req.getRequestDispatcher("/admin/users.jsp").forward(req,resp);
        }catch(Exception e){throw new ServletException(e);}
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            String action=req.getParameter("action");
            User u=new User();u.setEmail(req.getParameter("email"));u.setFullName(req.getParameter("fullName"));u.setAddress(req.getParameter("address"));u.setPhone(req.getParameter("phone"));u.setRoleId(Integer.parseInt(req.getParameter("roleId")));
            if("add".equals(action)){u.setPassword(req.getParameter("password"));dao.insert(u);}
            else {u.setId(Integer.parseInt(req.getParameter("id")));dao.update(u);}
            resp.sendRedirect("users");
        }catch(Exception e){throw new ServletException(e);}
    }
}
