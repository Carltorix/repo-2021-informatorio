package com.informatorio.Carrito.Controller;


import com.informatorio.Carrito.Entity.Usuario;
import com.informatorio.Carrito.Repository.UsuarioRepository;
import com.informatorio.Carrito.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class UsuarioController {


    private UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/usuario")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        try {
            return  ResponseEntity.ok(usuarioRepository.save(usuario));
        }catch (Exception e){
            return new ResponseEntity<>("El correo ya existe, cambielo", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<?> getUsuarioPorId(@PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(usuarioRepository.findById(id).get());
        }catch(Exception e){
            return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/usuario")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion,
            @RequestParam(name = "cuidad", required = false) String cuidad
    ) {
        return this.usuarioService.mostrarUsuarios(fechaDeCreacion,cuidad);
    }

    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<?> modificaPorIdUsuario(@PathVariable("id") Long id,
                                                  @RequestBody Usuario usuario) {
        try {
            try {
                return ResponseEntity.ok(this.usuarioService.modificarUsuario(id, usuario));
            }catch(Exception e){
                return new ResponseEntity<>("El mail es repetido", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/usuario/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable("id") Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).get();
            if(!usuario.getBaja()) {
                if (usuario.getCarritos().size() == 0) {
                    usuarioRepository.deleteById(id);
                    return ResponseEntity.ok("Borrado "+id);
                } else {
                    usuario.setBaja(true);
                    return ResponseEntity.ok("Baja "+id);
                }
            }
            return new ResponseEntity<>("El usuario esta dado de baja",HttpStatus.NOT_ACCEPTABLE);
        }catch(Exception e) {
            return new ResponseEntity<>("El usuario no existe o esta dado de baja", HttpStatus.NOT_FOUND);
        }
    }
}
