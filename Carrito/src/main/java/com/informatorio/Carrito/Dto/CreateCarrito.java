package com.informatorio.Carrito.Dto;

import javax.validation.constraints.NotBlank;

public class CreateCarrito {

    private String device;

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
