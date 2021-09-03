package com.informatorio.Carrito.Repository;

import com.informatorio.Carrito.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT pro FROM Producto pro where pro.nombre like %?1%")
    public List<Producto> findByNombre(String nombre);

    @Query("SELECT pro FROM Producto pro where pro.publicado = false ")
    public List<Producto> findAllBySinPlublicar();
}
