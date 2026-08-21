package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns={"/admin/*","/leader/*","/member/*"})
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException{
        HttpServletRequest r=(HttpServletRequest)req;
        HttpServletResponse s=(HttpServletResponse)res;
        HttpSession session=r.getSession(false);
        if(session==null || session.getAttribute("currentUser")==null){s.sendRedirect(r.getContextPath()+"/login");return;}
        chain.doFilter(req,res);
    }
}
