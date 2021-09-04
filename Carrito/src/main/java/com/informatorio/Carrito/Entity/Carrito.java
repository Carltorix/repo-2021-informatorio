package com.informatorio.Carrito.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String device;

    @CreationTimestamp
    private LocalDateTime fechaAlta;

    @UpdateTimestamp
    private LocalDateTime fechaUltimaModificacion;

    @Transient
    private String nombreDeUsuario;

    @Transient
    private double total = 0.0;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaCarrito> lineaCarritos = new ArrayList<>();

    private boolean cierre = false;

    public Carrito(){

    }
    public Carrito(String device){
        this.setDevice(device);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }


    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaDeCreacion) {
        this.fechaAlta = fechaDeCreacion;
    }


    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public void setNombreDeUsuario() {
        this.nombreDeUsuario = this.getUsuario().getNombreDeUsuario();
    }

    public String getNombreDeUsuario() {
        return usuario.getNombreDeUsuario();
    }


    public double getTotal() {
        for(LineaCarrito lineaCarrito:this.getLineaDeCarritos()){
            var subT = lineaCarrito.getSubTotal().doubleValue();
            this.total+=subT;
        }
        return this.total;
    }


    public boolean getCierre() { return cierre; }

    public void setCierre(boolean cierre) { this.cierre = cierre; }


    public List<LineaCarrito> getLineaDeCarritos() {
        return lineaCarritos;
    }

    public void agregarLineaCarrito(LineaCarrito lineaCarrito) {
        if(!this.getLineaDeCarritos().contains(lineaCarrito)){
            this.getLineaDeCarritos().add(lineaCarrito);
            lineaCarrito.setCarrito(this);
        }
    }

    public void removerLineaCarrito(LineaCarrito lineaCarrito) {
        if (!this.getLineaDeCarritos().isEmpty()){
            this.getLineaDeCarritos().remove(lineaCarrito);
            lineaCarrito.setCarrito(null);
        }
    }

}
