package com.informatorio.CarritoCompra.Controller;

import com.informatorio.CarritoCompra.Entity.Producto;
import com.informatorio.CarritoCompra.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping(value = "/producto")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping(value = "/producto/{id}")
    public Producto getProductoPorId(@PathVariable("id") Long id) {
        return productoRepository.findById(id).get();
    }

    @GetMapping(value = "/producto")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(productoRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/producto/{id}")
    public Producto modificarProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        Producto productoE = productoRepository.findById(id).get();
        productoE.setNombre(producto.getNombre());
        productoE.setPrecioUnitario(producto.getPrecioUnitario());
        productoE.setDescripcion(producto.getDescripcion());
        return productoRepository.save(productoE);
    }

    @DeleteMapping(value = "/producto/{id}")
    public void borrarPorId(@PathVariable("id") Long id) {
        productoRepository.deleteById(id);
    }

}
