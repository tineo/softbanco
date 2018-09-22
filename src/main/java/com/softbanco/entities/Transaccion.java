package com.softbanco.entities;


import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="transacciones")
public class Transaccion  implements Serializable{
    public static final int RETIRO = 1;
    public static final int DEPOSITO = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transaccion", unique=true, nullable=false)
    private int id_transaccion;

    @Column(name="nombre_transaccion")
    private String nombre_transaccion;

    @Column(name="monto")
    private int monto;

    @Column(name="fecha")
    private String fecha;

    @Column(name="tipo")
    private int tipo;

    @Column(name="id_cliente")
    private int id_cliente;

    @Column(name="codigo_operacion")
    private String codigo_operacion;

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

    public int getMonto() {
        return monto;
    }

    public Transaccion setMonto(int monto) {
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

    public int getId_cliente() {
        return id_cliente;
    }

    public Transaccion setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
        return this;
    }

    public String getCodigo_operacion() {
        return codigo_operacion;
    }

    public Transaccion setCodigo_operacion(String codigo_operacion) {
        this.codigo_operacion = codigo_operacion;
        return this;
    }
}
