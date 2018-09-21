package com.softbanco.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        System.out.println("SESSION");
        System.out.println(session);
        if (session == null) {   //checking whether the session exists
            this.context.log("Unauthorized access request 2");
            res.sendRedirect(req.getContextPath() + "/login.jsp");

        } else {
            if( session.getAttribute("user") == null){
                this.context.log("Unauthorized access request 2");
                res.sendRedirect(req.getContextPath() + "/login.jsp");
            }
            System.out.println("USER");
            System.out.println(session.getAttribute("user"));
            // pass the request along the filter chain
            chain.doFilter(req, res);

        }
    }

    public void destroy() {
        //close any resources here
    }

}
