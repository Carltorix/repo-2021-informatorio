package com.informatorio.Carrito.Repository;

import com.informatorio.Carrito.Entity.Producto;
import com.informatorio.Carrito.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT us FROM Usuario us where us.ciudad like %?1%")
    public List<Usuario> findByCiudad(String ciudad);

    //@Query("SELECT us FROM Usuario us where us.fechaAlta > ?1")
    public List<Usuario> findByFechaAltaAfter(LocalDate fecha);
}
