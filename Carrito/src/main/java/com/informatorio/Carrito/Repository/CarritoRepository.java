package com.informatorio.Carrito.Repository;

import com.informatorio.Carrito.Entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    List<Carrito> findByCierreFalse();
    List<Carrito> findByCierreTrue();
}
