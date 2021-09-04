package com.informatorio.Carrito.Controller;


import com.informatorio.Carrito.Entity.Producto;
import com.informatorio.Carrito.Entity.Usuario;
import com.informatorio.Carrito.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<?> getAll(
            @RequestParam(value = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion,
            @RequestParam(name = "cuidad", required = false) String cuidad
    ) {
        if (fechaDeCreacion != null){
            return new ResponseEntity(usuarioRepository.findByFechaAltaAfter(fechaDeCreacion), HttpStatus.OK);
        }else if (cuidad != null) {
            return new ResponseEntity(usuarioRepository.findByCiudad(cuidad), HttpStatus.OK);
        }else{
            return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/usuario/{id}")
    public Usuario modificarUsuario(@PathVariable("id") Long id,
                                    @RequestBody Usuario usuario) {
        Usuario usuarioE = usuarioRepository.findById(id).get();
        if(usuario.getNombreDeUsuario() != null) {
            usuarioE.setNombreDeUsuario(usuario.getNombreDeUsuario());
        }
        if(usuario.getDireccion() != null) {
            usuarioE.setDireccion(usuario.getDireccion());
        }
        if(usuario.getCiudad() != null) {
            usuarioE.setCiudad(usuario.getCiudad());
        }
        if(usuario.getProvincia() != null) {
            usuarioE.setProvincia(usuario.getProvincia());
        }
        if(usuario.getPaís() != null) {
            usuarioE.setPaís(usuario.getPaís());
        }
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
}
