<%@ page import="com.softbanco.services.OperacionesService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.softbanco.entities.Cliente" %>
<%@ page import="com.softbanco.entities.Transaccion" %>
<%@ page import="com.softbanco.services.ClienteService" %><%--
  Created by IntelliJ IDEA.
  User: tineo
  Date: 21/09/18
  Time: 01:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Banco</title>
    <link rel="stylesheet" href="css/estilos.css">
    <link rel="stylesheet" href="css/fonts.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</head>
<body>
<header>
    <div class="contenedor">
        <h2 class="titulo-banco">BANCO </h2>
        <a class="btn btn-primary btn-s" href="logout">Salir <span class="icon-exit"> </span> </a>
    </div>

</header>

<section class="user">
    <div class="contenedor-up">
        <h2 class="titulo-micuenta"><span class="icon-stack"> </span> Mi cuenta</h2>
        <div class="informacion">
            <div class="cuentas">
                <h2 class="titulo-cuenta">Numero de cuenta <span class="icon-pencil"> </span> </h2>
                <!-- <select class="input-cuenta" name="cuenta" id="cuenta">
                        <option value="null">--Seleccione--</option>
                        <option value="8">8</option>;
                </select> -->
                <h2 class="valor-cuenta">${ sessionScope.user.getNumero_cuenta() }</h2>
            </div>
            <div class="montoActual">
                <%
                    ClienteService service =  new ClienteService();
                    Cliente cliente = (Cliente) session.getAttribute("user");

                %>
                <h2 class="valor-Monto"><%=service.getSaldo(cliente.getId_cliente()) %></h2>
                <h2 class="titulo-Monto">Saldo disponible</h2>
            </div>
        </div>
    </div>

</section>


<section class="main">
    <section class="transaccion-tabla">
        <section class="transaccion">
            <section class="transaccion-pago">
                <div class="contenedor-center">
                    <div class="contenedor-center-up">
                        <h2 class="titulo-transaccion-pago"><span class="icon-coin-dollar"> </span>Transaccion</h2>
                        <form action="transferencia" method="POST" name="form-gasto" class="formulario">
                            <div class="datos">

                                <% if(session.getAttribute("error") != null) { %>
                                <div style="display: block; width: 100%" ><h6 style="color: red; font-weight: bold;"><%=session.getAttribute("error") %></h6></div>
                                <% } %>

                                <div class="contenedor-nota">
                                    <h2 class="titulo-input-cuenta">Cuenta</h2>
                                    <input type="text" class="input-nota" name="nro_cuenta" id="nota" placeholder="Numero de cuenta:" required />
                                </div>

                                <div class="contenedor-costo">
                                    <h2 class="titulo-costo">Monto</h2>
                                    <input type="text" class="input-costo"  name="monto" id="costo" placeholder="Costo:" required />
                                </div>

                            </div>
                            <input class="btn btn-primary btn-p" type="submit" value="Realizar pago">

                        </form>
                    </div>
                </div>
            </section>

        </section>

        <section class="tabla">
            <div class="contenedor-center">
                <div class="contenedor-center-down">
                    <div class="fomulario-tabla" >
                        <h2 class="titulo-tabla">TABLA DE TRANSACCIONES</h2>
                            <div class="tabla-container">
                                <table  class="tablaGastos">

                                    <%

                                        //HttpSession session = request.getSession(true);


                                        OperacionesService serv = new OperacionesService();
                                        ArrayList<Transaccion> lista = serv.getListOpeaciones(cliente);

                                    %>
                                    <thead>
                                    <Tr>
                                        <Th>Cuenta</Th><Th>Fecha / Hora</Th><Th>Operacion</Th><Th>Monto</Th>
                                    </Tr>
                                    </thead>
                                    <tbody>
                                    <% for(int i = 0; i < lista.size(); i+=1) {
                                        String estilo = "", signo ="";
                                        if (lista.get(i).getTipo() == Transaccion.RETIRO) {

                                            estilo = "style=\"color: darkred\"";
                                            signo = "-";
                                        }

                                    %>
                                    <Tr>
                                        <Td><%=service.getCuentaByCodOpe(lista.get(i).getCodigo_operacion(), lista.get(i).getTipo() )%></Td>
                                        <Td><%=lista.get(i).getFecha()%></Td>
                                        <Td><%=lista.get(i).getNombre_transaccion()%></Td>
                                        <Td <%=estilo %> >  <%=signo %> <%=lista.get(i).getMonto()%></Td>

                                    </Tr>
                                    <% } %>
                                    </tbody>
                                    <!-- <?php foreach ($transacciones as $transaccion): ?>
                                        <Tr>
                                            <Td><?php echo $transaccion['numCuenta'] ?></Td><Td><?php echo $transaccion['monto'] ?></Td>

                                        </Tr>
                                    <?php endforeach ?> -->
                                </table>
                            </div>
                    </div>
                </div>
            </div>
        </section>
    </section>

</section>

</body>
</html>