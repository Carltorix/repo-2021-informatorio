package com.informatorio.CarritoCompra.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 4)
    private String nombre;

    @NotBlank
    @Size(min = 4)
    private String apellido;

    @NotBlank
    private String direccion;

    @CreationTimestamp
    private LocalDateTime fechaAlta;

    @UpdateTimestamp
    private LocalDateTime fechaUltimaModificacion;


    public Usuario() {

    }

    public Usuario(String nombre,String apellido,String direccion) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDireccion(direccion);
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }
}
