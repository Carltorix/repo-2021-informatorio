package com.informatorio.Carrito.Dto;


import java.io.Serializable;

public class CreateCarrito implements Serializable {

    private String device;

    public CreateCarrito(){

    }
    public CreateCarrito(String device) {
        this.device = device;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
