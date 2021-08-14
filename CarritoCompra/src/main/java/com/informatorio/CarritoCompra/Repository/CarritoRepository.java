package com.informatorio.CarritoCompra.Repository;

import com.informatorio.CarritoCompra.Entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {


}
