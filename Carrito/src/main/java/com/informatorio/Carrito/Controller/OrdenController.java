package com.informatorio.Carrito.Controller;

import com.informatorio.Carrito.Dto.IdCarrito;
import com.informatorio.Carrito.Repository.*;
import com.informatorio.Carrito.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdenController  {

    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private LineaCarritoRepository lineaCarritoRepository;
    private OrdenRepository ordenRepository;
    private OrdenService ordenService;

    @Autowired
    public OrdenController(CarritoRepository carritoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository,
                           LineaCarritoRepository lineaCarritoRepository, OrdenRepository ordenRepository,
                           OrdenService ordenService
    ) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.lineaCarritoRepository = lineaCarritoRepository;
        this.ordenRepository = ordenRepository;
        this.ordenService = ordenService;
    }

    @GetMapping(value = "/orden/{id}")
    public ResponseEntity<?> getOrdenPorId(@PathVariable("id") Long id) {
        var orden = ordenRepository.findById(id).orElse(null);
        return (orden != null) ?
                ResponseEntity.ok(orden)
                : new ResponseEntity<>(
                "Orden not found",
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping(value = "/orden")
    public ResponseEntity<?> getAll(@RequestParam(name = "estado", required = false) String estado) {
        return this.ordenService.mostrarOrdenes(estado);
    }

    @DeleteMapping(value = "/orden/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable("id") Long id) {

        return this.ordenService.borrarOrdenId(id);

    }

    @GetMapping(value = "usuario/{id}/orden")
    public ResponseEntity<?> getOrdenPorUser(@PathVariable("id") Long id) {

        return new ResponseEntity<>(this.ordenService.mostrarOrdenIdUser(id),HttpStatus.OK);

    }

    @PostMapping(value = "usuario/{id}/orden")
    public ResponseEntity<?> crearOrden(@PathVariable("id") Long id,
                                        @RequestBody IdCarrito idCarrito){

        return this.ordenService.crearCargarOrden(idCarrito);
    }

}
