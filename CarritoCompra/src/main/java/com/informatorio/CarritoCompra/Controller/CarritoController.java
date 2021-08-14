package com.informatorio.CarritoCompra.Controller;

import com.informatorio.CarritoCompra.Entity.Carrito;
import com.informatorio.CarritoCompra.Entity.LineaCarrito;
import com.informatorio.CarritoCompra.Entity.Usuario;
import com.informatorio.CarritoCompra.Repository.CarritoRepository;
import com.informatorio.CarritoCompra.Repository.UsuarioRepository;
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

    @PostMapping(value = "usuario/{id}/carrito")
    public Carrito createCarrito(@PathVariable("id") Long usuarioId, @Valid @RequestBody Carrito carrito) {
        Usuario usuario = usuarioRepository.getById(usuarioId);
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

    @PutMapping(value = "/carrito/{id}")
    public Carrito modificarCarrito(@PathVariable("id") Long id, @RequestBody Carrito carrito) {
        Carrito carritoE = carritoRepository.findById(id).get();
        carritoE.setDevice(carrito.getDevice());
        return carritoRepository.save(carritoE);
    }

    @DeleteMapping(value = "/carrito/{id}")
    public void borrarPorId(@PathVariable("id") Long id) {
        carritoRepository.deleteById(id);
    }

}
