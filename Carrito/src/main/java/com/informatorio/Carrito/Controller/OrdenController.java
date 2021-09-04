package com.informatorio.Carrito.Controller;

import com.informatorio.Carrito.Dto.IdCarrito;
import com.informatorio.Carrito.Entity.Carrito;
import com.informatorio.Carrito.Entity.LineaCarrito;
import com.informatorio.Carrito.Entity.LineaDetalle;
import com.informatorio.Carrito.Entity.Orden;
import com.informatorio.Carrito.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdenController  {

    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private LineaCarritoRepository lineaCarritoRepository;
    private OrdenRepository ordenRepository;

    @Autowired
    public OrdenController(CarritoRepository carritoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository, LineaCarritoRepository lineaCarritoRepository, OrdenRepository ordenRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.lineaCarritoRepository = lineaCarritoRepository;
        this.ordenRepository = ordenRepository;
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
        if(estado != null){
            if(estado.equalsIgnoreCase("true")){
                return new ResponseEntity(ordenRepository.findByBorrarTrue(), HttpStatus.OK);
            }
            return new ResponseEntity(ordenRepository.findByBorrarFalse(), HttpStatus.OK);
        }
        return new ResponseEntity(ordenRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/orden/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable("id") Long id) {
        var orden = ordenRepository.findById(id).orElse(null);
        if(orden != null){
            orden.setBorrar(true);
        }
        return (orden != null) ?
                ResponseEntity.ok("Se borro la orden N° "+orden.getId())
                : new ResponseEntity<>(
                "user not found",
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping(value = "usuario/{id}/orden")
    public ResponseEntity<?> getOrdenPorUser(@PathVariable("id") Long id) {
        var usuario =  usuarioRepository.findById(id).orElse(null);
        var orden = ordenRepository.findByUsuario(usuario);

        return (orden.size()>0) ?
                ResponseEntity.ok(orden)
                : new ResponseEntity<>(
                "orden not found",
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping(value = "usuario/{id}/orden")
    public ResponseEntity<?> crearOrden(@PathVariable("id") Long id,
                                        @RequestBody IdCarrito idCarrito){
        if (idCarrito != null) {
            Carrito carrito = carritoRepository.findById(idCarrito.getId()).orElse(null);
            if(carrito != null){
                if(!carrito.getCierre()) {
                    Orden orden = new Orden();
                    orden.setObservacion(idCarrito.getObservacion());
                    orden.setUsuario(carrito.getUsuario());
                    orden.setCarrito(carrito);
                    for (LineaCarrito lineaCarrito : carrito.getLineaDeCarritos()) {
                        LineaDetalle lineaDetalle = new LineaDetalle();
                        lineaDetalle.setCantidad(lineaCarrito.getCantidad());
                        lineaDetalle.setProducto(lineaCarrito.getProducto());
                        lineaDetalle.setPrecioUnitario(lineaCarrito.getProducto().getPrecioUnitario());
                        orden.agregarLineaDetalle(lineaDetalle);
                    }
                    carrito.setCierre(true);
                    return ResponseEntity.ok(ordenRepository.save(orden));
                }
            }
        }
        return new ResponseEntity<>("Carrito not found", HttpStatus.NOT_FOUND);

    }

}
