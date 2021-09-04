package com.informatorio.Carrito.Controller;

import com.informatorio.Carrito.Dto.CreateCarrito;
import com.informatorio.Carrito.Dto.OperacionCarrito;
import com.informatorio.Carrito.Service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarritoController {

    private CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {

        this.carritoService = carritoService;
    }

    @PostMapping(value = "usuario/{id}/carrito")
    public ResponseEntity<?> createCarrito(@PathVariable("id") Long id,
                                           @RequestBody CreateCarrito createCarrito) {

        return new ResponseEntity<>(this.carritoService.crearCar(id,createCarrito.getDevice()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/carrito/{id}")
    public ResponseEntity<?> getCarritoPorId(@PathVariable("id") Long id) {

        return this.carritoService.mostrarCarrito(id);
    }

    @GetMapping(value = "/carrito")
    public ResponseEntity<?> getAll(@RequestParam(value = "estado", required = false) String estado) {

        return this.carritoService.mostrarCarritos(estado);
    }

    @DeleteMapping(value = "/carrito/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable("id") Long id) {

        return this.carritoService.borrarCarrito(id);

    }

    @PutMapping(value = "usuario/{id}/carrito/{idCarrito}")
    public ResponseEntity<?> agregarProductoAlCarrito(@PathVariable("id") Long id,
                                             @PathVariable("idCarrito") Long idCarrito,
                                             @RequestBody OperacionCarrito operacionCarrito) {

       return this.carritoService.agregarProducto(id,idCarrito,operacionCarrito);
    }

    @DeleteMapping("/usuario/{id}/carrito/{idCarrito}/producto/{idProducto}")
    public ResponseEntity<?> borrarProducto(@PathVariable("id") Long userId,
                               @PathVariable("idCarrito") Long idCarrito,
                               @PathVariable("idProducto") Long idProducto) {

        return this.carritoService.borrarLineaCarritoProducto(idProducto);
    }
}
