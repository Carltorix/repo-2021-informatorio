package com.informatorio.Carrito.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class LineaCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @JoinColumn(name = "carrito_id")
    @ManyToOne(fetch = FetchType.EAGER)
    Carrito carrito;

    @ManyToOne(fetch = FetchType.EAGER)
    Producto producto;

    private Integer cantidad;

    @Transient
    private BigDecimal subTotal;

    @Transient
    private BigDecimal precioUnitario;

    public LineaCarrito(){

    }

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
        return this.subTotal = this.getProducto().getPrecioUnitario().multiply(new BigDecimal(this.getCantidad()));
    }

    public BigDecimal getPrecioUnitario() {
       return this.precioUnitario = this.getProducto().getPrecioUnitario();
    }



}
