package com.informatorio.Carrito.Entity;

import com.informatorio.CarritoCompra.util.ValidationHelper;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Email(regexp = ValidationHelper.EMAIL_REGEX)
    private String nombreDeUsuario;

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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carrito> carritos = new ArrayList<>();

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

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
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

    public void agregarCarrito(Carrito carrito) {
        if(!carritos.contains(carrito)){
            carritos.add(carrito);
            carrito.setUsuario(this);
        }
    }

    public void removerCarrito(Carrito carrito) {
        if (!carritos.isEmpty()){
            carritos.remove(carrito);
            carrito.setUsuario(null);
        }
    }
}
