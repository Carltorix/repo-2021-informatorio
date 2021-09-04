package com.informatorio.Carrito.Service;

import com.informatorio.Carrito.Entity.Usuario;
import com.informatorio.Carrito.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> mostrarUsuarios(LocalDate fechaDeCreacion, String ciudadEle){

        if (fechaDeCreacion != null){
            return new ResponseEntity(usuarioRepository.findByFechaAltaAfter(fechaDeCreacion), HttpStatus.OK);
        }else if (ciudadEle != null) {
            return new ResponseEntity(usuarioRepository.findByCiudad(ciudadEle), HttpStatus.OK);
        }
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    public Usuario modificarUsuario(Long id,Usuario usuario){

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


}
