package com.softbanco.services;

import com.softbanco.entities.Cliente;
import com.softbanco.entities.Transaccion;
import com.softbanco.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OperacionesService {

    public ArrayList<Transaccion> getListOpeaciones() {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        ArrayList lista = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Transaccion");
            lista = (ArrayList<Transaccion>)query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }
}
