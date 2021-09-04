package com.informatorio.Carrito.Entity;

import com.informatorio.Carrito.util.ValidationHelper;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(unique = true)
    //@Email(regexp = ValidationHelper.EMAIL_REGEX)
    private String nombreDeUsuario;

    //@NotBlank
    @Size(min = 8)
    private String password;

    @CreationTimestamp
    private LocalDate fechaAlta;

    @UpdateTimestamp
    private LocalDate fechaUltimaModificacion;

    private String direccion;

    private String ciudad;

    private String provincia;

    private String país;

    private boolean baja = false;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carrito> carritos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orden> ordenes = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombre,String apellido,String nombreDeUsuario) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setNombreDeUsuario(nombreDeUsuario);
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public LocalDate getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPaís() {
        return país;
    }

    public void setPaís(String país) {
        this.país = país;
    }


    public List<Carrito> getCarritos() { return carritos; }

    public void agregarCarrito(Carrito carrito) {
        if(!this.getCarritos().contains(carrito)){
            this.getCarritos().add(carrito);
            carrito.setUsuario(this);
        }
    }

    public void removerCarrito(Carrito carrito) {
        if (!this.getCarritos().isEmpty()){
            this.getCarritos().remove(carrito);
            carrito.setUsuario(null);
        }
    }

    public List<Orden> getOrdenes() { return ordenes; }

    public void agregarOrden(Orden orden) {
        if(!this.getOrdenes().contains(orden)){
            this.getOrdenes().add(orden);
            orden.setUsuario(this);
        }
    }

    public void removerOrden(Orden orden) {
        if (!this.getOrdenes().isEmpty()){
            this.getOrdenes().remove(orden);
            orden.setUsuario(null);
        }
    }

    public boolean getBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }
}
