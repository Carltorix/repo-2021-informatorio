package com.informatorio.Carrito.Controller;


import com.informatorio.Carrito.Entity.Producto;
import com.informatorio.Carrito.Entity.Usuario;
import com.informatorio.Carrito.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/usuario")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioRepository.findById(id).get();
    }

    @GetMapping(value = "/usuario")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/usuario/{id}")
    public Usuario modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Usuario usuarioE = usuarioRepository.findById(id).get();
        usuarioE.setDireccion(usuario.getDireccion());
        return usuarioRepository.save(usuarioE);
    }

    @DeleteMapping(value = "/usuario/{id}")
    public void borrarPorId(@PathVariable("id") Long id) {

        Usuario usuario = usuarioRepository.findById(id).get();
        if(!usuario.getBaja()) {
            if (usuario.getCarritos().size() == 0) {
                usuarioRepository.deleteById(id);
            } else {
                usuario.setBaja(true);
            }
        }
    }

    @GetMapping(value = "/usuario/ciudad/{ciudad}")
    public List<Usuario> getUsuarioCiudad(@PathVariable("ciudad") String ciudad) {
        return usuarioRepository.findByCiudad(ciudad);
    }

    @GetMapping(value = "/usuario/fecha/{fecha}")
    public List<Usuario> getUsuarioFecha(@PathVariable("fecha") String fecha) {
        return usuarioRepository.findAllByFechaCreacion(fecha);
    }
}
