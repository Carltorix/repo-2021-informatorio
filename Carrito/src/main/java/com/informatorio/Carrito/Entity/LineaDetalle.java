package com.informatorio.Carrito.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class LineaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Orden orden;

    @ManyToOne(fetch = FetchType.LAZY)
    Producto producto;

    @NotBlank
    private Integer cantidad;

    @Transient
    private BigDecimal subTotal;

    @NotBlank
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


    public void setPrecioUnitario() {
        this.precioUnitario = this.getProducto().getPrecioUnitario();
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }


    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal() {
        this.subTotal = this.getProducto().getPrecioUnitario().multiply(new BigDecimal(this.getCantidad()));
    }
}
