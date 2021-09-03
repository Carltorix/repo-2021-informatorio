package com.informatorio.Carrito.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class LineaCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Carrito carrito;

    @ManyToOne(fetch = FetchType.LAZY)
    Producto producto;

    private Integer cantidad;

    @Transient
    private BigDecimal subTotal;

    @Transient
    private BigDecimal precioUnitario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setPrecioUnitario() {
        this.precioUnitario = this.getProducto().getPrecioUnitario();
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setSubTotal() {
        this.subTotal = this.getProducto().getPrecioUnitario().multiply(new BigDecimal(this.getCantidad()));
    }
}
