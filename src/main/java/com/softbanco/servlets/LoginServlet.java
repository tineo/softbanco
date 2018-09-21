package com.softbanco.servlets;

import com.google.gson.Gson;
import com.softbanco.entities.Cliente;
import com.softbanco.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Consumer;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("username");
        String password = request.getParameter("password");
        LoginService loginService = new LoginService();
        boolean result = loginService.authenticate(userId, password);
        Cliente user = loginService.getClienteByNumeroTarjeta(userId);
        if(result == true){
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home.jsp");
        }
        else{
            response.sendRedirect("login.jsp");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("GET");
        System.out.println("u:"+request.getParameter("username"));
        System.out.println("p:"+request.getParameter("password"));
        request.getParameterMap().values().forEach(strings -> System.out.println(strings));

        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("POST");
        System.out.println("u:"+request.getParameter("username"));
        System.out.println("p:"+request.getParameter("password"));
        //processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}