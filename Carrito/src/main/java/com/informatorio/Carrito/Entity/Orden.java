package com.informatorio.Carrito.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Carrito carrito;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDetalle> LineaDetalles = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime fechaAlta;

    @Size(max = 200)
    private String observacion ;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public Orden() {

    }
    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaDeCreacion) {
        this.fechaAlta = fechaDeCreacion;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public List<LineaDetalle> getLineaDetalles() {
        return LineaDetalles;
    }

    public void agregarLineaDetalle(LineaDetalle lineaDetalle) {
        if(!this.getLineaDetalles().contains(lineaDetalle)){
            this.getLineaDetalles().add(lineaDetalle);
            lineaDetalle.setOrden(this);
        }
    }

    public void removerLineaDetalle(LineaDetalle lineaDetalle) {
        if (!this.getLineaDetalles().isEmpty()){
            this.getLineaDetalles().remove(lineaDetalle);
            lineaDetalle.setOrden(null);
        }
    }


    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }


    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

}
