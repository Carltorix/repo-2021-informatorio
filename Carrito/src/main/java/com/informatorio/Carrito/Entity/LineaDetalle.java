package com.informatorio.Carrito.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class LineaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    Orden orden;

    @ManyToOne(fetch = FetchType.EAGER)
    Producto producto;

    //@NotBlank
    private Integer cantidad;

    @Transient
    private BigDecimal subTotal;

    //@NotBlank
    private BigDecimal precioUnitario;

    public LineaDetalle() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Orden getOrden() { return orden; }

    public void setOrden(Orden orden) { this.orden = orden; }


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioUnitario() {

        return this.precioUnitario;
    }

    public BigDecimal getSubTotal() {
         return this.subTotal = this.getPrecioUnitario().multiply(new BigDecimal(this.getCantidad()));
    }
}
