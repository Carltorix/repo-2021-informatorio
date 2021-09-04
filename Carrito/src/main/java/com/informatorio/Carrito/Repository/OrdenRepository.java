package com.informatorio.Carrito.Repository;

import com.informatorio.Carrito.Entity.Orden;
import com.informatorio.Carrito.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long>  {

    List<Orden> findByBorrarFalse();
    List<Orden> findByBorrarTrue();
    List<Orden> findByUsuario(Usuario usuario);
}
