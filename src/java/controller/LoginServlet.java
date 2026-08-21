package controller;

import model.User;
import service.LoginService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService service;
    public void init(){service=new LoginService();}
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        try{
            User u=service.login(req.getParameter("email"),req.getParameter("password"));
            if(u==null){req.setAttribute("error","Email hoặc mật khẩu không đúng.");req.getRequestDispatcher("/login.jsp").forward(req,resp);return;}
            req.getSession().setAttribute("currentUser",u);
            if("ADMIN".equals(u.getRoleName()))resp.sendRedirect(req.getContextPath()+"/admin/home");
            else if("LEADER".equals(u.getRoleName()))resp.sendRedirect(req.getContextPath()+"/leader/home");
            else resp.sendRedirect(req.getContextPath()+"/member/home");
        }catch(Exception e){throw new ServletException(e);}
    }
}
