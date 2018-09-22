package com.softbanco.services;

import com.softbanco.entities.Cliente;
import com.softbanco.entities.Transaccion;
import com.softbanco.util.HibernateUtil;
//import org.hibernate.Query;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static java.lang.Math.toIntExact;

import java.util.ArrayList;

public class ClienteService {

    public Cliente getClienteByNroCuenta(String nro_cuenta) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Cliente cliente = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            Query query = session
                    .createQuery("from Cliente WHERE numero_cuenta = :numero_cuenta")
                    .setParameter("numero_cuenta", nro_cuenta);
            cliente = (Cliente) query.getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cliente;
    }

    public String getCuentaByCodOpe(String cod_ope, int tipo) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Cliente cliente = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            Query query;
            if(tipo == Transaccion.RETIRO) {

                query = session
                        .createQuery("from Transaccion" +
                                " WHERE codigo_operacion = :cod_op" +
                                " AND tipo = " + Transaccion.DEPOSITO)
                        .setParameter("cod_op", cod_ope);
            }else{
                query = session
                        .createQuery("from Transaccion" +
                                " WHERE codigo_operacion = :cod_op" +
                                " AND tipo = " + Transaccion.RETIRO)
                        .setParameter("cod_op", cod_ope);
            }

            Transaccion transaccion = (Transaccion) query.getSingleResult();


            Query query2 = session
                    .createQuery("from Cliente" +
                            " WHERE id_cliente = :id_cliente")
                    .setParameter("id_cliente", transaccion.getId_cliente());

            cliente = (Cliente) query2.getSingleResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        //Cuentas de paraiso fiscal
        if(cliente == null) return "187654321-CAIMAN";

        return cliente.getNumero_cuenta();

    }

    public int getSaldo(int id_cliente) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Cliente cliente = null;
        int saldo = 666;
        try {

            tx = session.getTransaction();
            tx.begin();

            Query query = session
                    .createQuery("select sum(monto) " +
                            "from Transaccion where id_cliente = :id_cliente " +
                            "AND tipo = "+Transaccion.DEPOSITO)
                    .setParameter("id_cliente", id_cliente);
            Long depositos = (Long) query.getSingleResult();


            Query query1 = session
                    .createQuery("select sum(monto) " +
                            "from Transaccion where id_cliente = :id_cliente " +
                            "AND tipo = "+Transaccion.RETIRO)
                    .setParameter("id_cliente", id_cliente);
            Long retiros = (Long) query1.getSingleResult();


            saldo = toIntExact(depositos) - toIntExact(retiros);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return saldo;

    }

}
