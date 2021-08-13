package com.informatorio.CarritoCompra.Repository;

import com.informatorio.CarritoCompra.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {


}
