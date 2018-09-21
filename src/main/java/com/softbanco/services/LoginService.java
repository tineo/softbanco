package com.softbanco.services;


import java.util.ArrayList;
import java.util.List;
import com.softbanco.entities.Cliente;
import com.softbanco.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginService {
    public boolean authenticate(String tarjeta, String password) {
        Cliente cliente = getClienteByNumeroTarjeta(tarjeta);
        if(cliente!=null && cliente.getNumero_tarjeta().equals(tarjeta) && cliente.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }
    public Cliente getClienteByNumeroTarjeta(String tarjeta) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Cliente cliente = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Cliente where numero_tarjeta='"+tarjeta+"'");
            cliente = (Cliente)query.uniqueResult();
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

    /*public List<User> getListOfUsers(){
        List<User> list = new ArrayList<User>();
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            list = session.createQuery("from User").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }*/
}