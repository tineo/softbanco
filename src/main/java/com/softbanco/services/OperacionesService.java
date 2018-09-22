package com.softbanco.services;

import com.softbanco.entities.Cliente;
import com.softbanco.entities.Transaccion;
import com.softbanco.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import java.util.ArrayList;

public class OperacionesService {

    public ArrayList<Transaccion> getListOpeaciones(Cliente cliente) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        ArrayList lista = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session
                    .createQuery("from Transaccion WHERE id_cliente = :id_cliente")
                    .setParameter("id_cliente", cliente.getId_cliente());
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

    public int makeTransaccion(Cliente sender, Cliente receiver, int monto){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        DateFormat sdf = new SimpleDateFormat("MM/dd");
        Date now = Calendar.getInstance().getTime();
        String current = df.format(now);
        String scur = sdf.format(now);


        Transaccion t01 =  new Transaccion();
        t01
                .setCodigo_operacion(randomUUIDString)
                .setFecha(current)
                .setId_cliente(sender.getId_cliente())
                .setTipo(Transaccion.RETIRO)
                .setMonto(monto)
                .setNombre_transaccion("RET "+scur);

        Transaccion t02 =  new Transaccion();
        t02
                .setCodigo_operacion(randomUUIDString)
                .setFecha(current)
                .setId_cliente(receiver.getId_cliente())
                .setTipo(Transaccion.DEPOSITO)
                .setMonto(monto)
                .setNombre_transaccion("DEP "+scur);

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        ArrayList lista = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            session.persist(t01);
            session.persist(t02);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return t01.getId_transaccion();

    }
}
