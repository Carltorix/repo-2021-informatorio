package com.informatorio.Carrito.Dto;

import java.io.Serializable;

public class IdCarrito implements Serializable {

    private long id;
    private String observacion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
