package com.informatorio.Carrito.Service;

import com.informatorio.Carrito.Entity.Producto;
import com.informatorio.Carrito.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> mostrarProductos(String nombre,String estado){

        if(nombre != null) {
            return productoRepository.findByNombreContaining(nombre);
        }else if(estado != null){
            if(estado.equalsIgnoreCase("false")){
                return productoRepository.findByPublicadoFalse();
            }else{
                return productoRepository.findByPublicadoTrue();
            }
        }
        return productoRepository.findAll();
    }

    public Producto modificarProducto(Long id,Producto producto){
        ;
        Producto productoE = productoRepository.findById(id).get();
        if(producto.getNombre() != null) {
            productoE.setNombre(producto.getNombre());
        }
        if(producto.getPrecioUnitario() != null) {
            productoE.setPrecioUnitario(producto.getPrecioUnitario());
        }
        if(producto.getDescripcion() != null) {
            productoE.setDescripcion(producto.getDescripcion());
        }
        if(producto.getPublicado() != productoE.getPublicado()) {
            productoE.setPublicado(producto.getPublicado());
        }
        return productoRepository.save(productoE);
    }
}
