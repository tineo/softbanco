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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClienteService service = new ClienteService();



        String nro_cuenta = (String) request.getParameter("nro_cuenta");
        int monto = Integer.parseInt(request.getParameter("monto"));

        Cliente receiver = service.getClienteByNroCuenta(nro_cuenta);
        HttpSession session = request.getSession(true);

        if( receiver == null ) {
            session.setAttribute("error","Cuenta no es valida");
            response.sendRedirect("index.jsp");
        }else{
            session.setAttribute("error",null);

        }

        System.out.println("RECEIVER");
        System.out.println(receiver.getId_cliente());

        Cliente cliente = (Cliente) session.getAttribute("user");
        OperacionesService service2 =  new OperacionesService();
        int id_transaccion = service2.makeTransaccion(cliente,receiver, monto );

        /*String json = new Gson().toJson(id_transaccion);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);*/

        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
