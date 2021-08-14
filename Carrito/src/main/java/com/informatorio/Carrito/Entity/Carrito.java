package com.informatorio.Carrito.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaCarrito> lineaCarritos = new ArrayList<>();

    @Transient
    private String nombreDeUsuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

    public String getNombreDeUsuario() {
        return usuario.getNombreDeUsuario();
    }
}
