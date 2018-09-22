package com.softbanco.servlets;

import com.google.gson.Gson;
import com.softbanco.entities.Cliente;
import com.softbanco.entities.Transaccion;
import com.softbanco.services.OperacionesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OperacionesServlet", urlPatterns = {"/operaciones"})
public class OperacionesServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("user");
        OperacionesService service =  new OperacionesService();
        ArrayList<Transaccion> transacciones =
                service.getListOpeaciones(cliente);

        String json = new Gson().toJson(transacciones);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
