package com.softbanco.entities;


import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="transacciones")
public class Transaccion  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_transaccion", unique=true, nullable=false)
    private int id_transaccion;

    @Column(name="nombre_transaccion")
    private String nombre_transaccion;

    @Column(name="monto")
    private String monto;

    @Column(name="fecha")
    private String fecha;

    @Column(name="tipo")
    private int tipo;

    public int getId_transaccion() {
        return id_transaccion;
    }

    public Transaccion setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
        return this;
    }

    public String getNombre_transaccion() {
        return nombre_transaccion;
    }

    public Transaccion setNombre_transaccion(String nombre_transaccion) {
        this.nombre_transaccion = nombre_transaccion;
        return this;
    }

    public String getMonto() {
        return monto;
    }

    public Transaccion setMonto(String monto) {
        this.monto = monto;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public Transaccion setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public int getTipo() {
        return tipo;
    }

    public Transaccion setTipo(int tipo) {
        this.tipo = tipo;
        return this;
    }
}
