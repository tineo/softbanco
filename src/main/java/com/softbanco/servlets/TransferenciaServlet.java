package com.softbanco.servlets;

import com.google.gson.Gson;
import com.softbanco.entities.Cliente;
import com.softbanco.entities.Transaccion;
import com.softbanco.services.ClienteService;
import com.softbanco.services.OperacionesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TransferenciaServlet", urlPatterns = {"/transferencia"})
public class TransferenciaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nro_cuenta = request.getParameter("nro_cuenta");
        int monto = Integer.parseInt(request.getParameter("nro_cuenta"));
        ClienteService service = new ClienteService();
        Cliente receiver = service.getClienteByNroCuenta(nro_cuenta);

        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("user");
        OperacionesService service2 =  new OperacionesService();
        int id_transaccion = service2.makeTransaccion(cliente,receiver, monto );

        String json = new Gson().toJson(id_transaccion);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
