package com.informatorio.Carrito.Repository;


import com.informatorio.Carrito.Entity.LineaCarrito;
import com.informatorio.Carrito.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaCarritoRepository extends JpaRepository<LineaCarrito,Long> {

    LineaCarrito findByProducto(Producto producto);
}
