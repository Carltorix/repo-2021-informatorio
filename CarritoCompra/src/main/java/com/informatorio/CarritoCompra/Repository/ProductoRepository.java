package com.informatorio.CarritoCompra.Repository;

import com.informatorio.CarritoCompra.Entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
