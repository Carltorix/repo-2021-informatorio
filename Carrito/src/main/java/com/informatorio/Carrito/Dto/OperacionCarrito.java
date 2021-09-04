package com.informatorio.Carrito.Dto;

import java.io.Serializable;

public class OperacionCarrito implements Serializable {

    private Integer cantidad;
    private Long productoId;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

}
