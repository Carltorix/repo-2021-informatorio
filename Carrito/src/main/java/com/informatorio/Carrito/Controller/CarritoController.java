package com.informatorio.Carrito.Controller;

import com.informatorio.Carrito.Dto.OperacionCarrito;
import com.informatorio.Carrito.Entity.Carrito;
import com.informatorio.Carrito.Entity.LineaCarrito;
import com.informatorio.Carrito.Entity.Producto;
import com.informatorio.Carrito.Entity.Usuario;
import com.informatorio.Carrito.Repository.CarritoRepository;
import com.informatorio.Carrito.Repository.LineaCarritoRepository;
import com.informatorio.Carrito.Repository.ProductoRepository;
import com.informatorio.Carrito.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private LineaCarritoRepository lineaCarritoRepository;

    @PostMapping(value = "usuario/{id}/carrito")
    public Carrito createCarrito(@PathVariable("id") Long usuarioId, @Valid @RequestBody Carrito carrito) {
        Usuario usuario = usuarioRepository.getById(usuarioId);
        //controlar que el id exista
        carrito.setUsuario(usuario);
        return carritoRepository.save(carrito);
    }

    @GetMapping(value = "/carrito/{id}")
    public Carrito getCarritoPorId(@PathVariable("id") Long id) {
        return carritoRepository.findById(id).get();
    }

    @GetMapping(value = "/carrito")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(carritoRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/carrito/{id}")
    public void borrarPorId(@PathVariable("id") Long id) {
        carritoRepository.deleteById(id);
    }

    @PutMapping(value = "usuario/{id}/carrito/{idCarrito}")
    public ResponseEntity<?> agregarProducto(@PathVariable("id") Long id,
                                             @PathVariable("id") Long idCarrito,
                                             @RequestBody OperacionCarrito operacionCarrito) {
        Carrito carritoE = carritoRepository.findById(idCarrito).get();
        Producto producto = productoRepository.getById(operacionCarrito.getProductoId());
        LineaCarrito lineaCarrito = new LineaCarrito();
        lineaCarrito.setProducto(producto);
        lineaCarrito.setCantidad(operacionCarrito.getCantidad());
        carritoE.agregarLineaCarrito(lineaCarrito);
        return new ResponseEntity<>(carritoRepository.save(carritoE), HttpStatus.CREATED);
    }

    @DeleteMapping("/usuario/{id}/carrito/{idCarrito}/linea/{idLinea}}")
    public void borrarProducto(@PathVariable("id") Long userId,
                                            @PathVariable("idCarrito") Long idCarrito,
                                            @PathVariable("idLinea") Long idLinea) {
        lineaCarritoRepository.deleteById(idLinea);

    }
}
