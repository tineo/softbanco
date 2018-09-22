package com.softbanco.servlets;

import com.google.gson.Gson;
import com.softbanco.entities.Transaccion;
import com.softbanco.services.OperacionesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OperacionesServlet", urlPatterns = {"/operaciones"})

public class OperacionesServlet extends HttpServlet {

    //@EJB(mappedName = "RecordManagedBean")
    //private RecordManagedBean RecordManagedBean;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OperacionesService service =  new OperacionesService();
        ArrayList<Transaccion> transacciones =  service.getListOpeaciones();

        String ejm = "oe/e";
        String json = new Gson().toJson(transacciones);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
