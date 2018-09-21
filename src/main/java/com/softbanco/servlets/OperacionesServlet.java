package com.softbanco.servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OperacionesServlet", urlPatterns = {"/json"})

public class OperacionesServlet extends HttpServlet {

    //@EJB(mappedName = "RecordManagedBean")
    //private RecordManagedBean RecordManagedBean;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String ejm = "oe/e";
        String json = new Gson().toJson(ejm);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
