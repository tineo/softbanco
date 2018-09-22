package com.softbanco.services;

import com.softbanco.entities.Cliente;
import com.softbanco.entities.Transaccion;
import com.softbanco.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            cliente = (Cliente)query.getSingleResult();
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
}
