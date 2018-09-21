package com.softbanco.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_cliente", unique=true, nullable=false)
    private int id_cliente;

    @Column(name="nombre")
    private String nombre;

    @Column(name="numero_tarjeta")
    private String numero_tarjeta;

    @Column(name="usuario")
    private String usuario;

    @Column(name="password")
    private String password;

    @Column(name="saldo")
    private int saldo;

    public int getId_cliente() {
        return id_cliente;
    }

    public Cliente setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Cliente setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public Cliente setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public Cliente setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Cliente setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getSaldo() {
        return saldo;
    }

    public Cliente setSaldo(int saldo) {
        this.saldo = saldo;
        return this;
    }
}
