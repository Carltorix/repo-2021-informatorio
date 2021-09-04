package com.informatorio.Carrito.Controller;

import com.informatorio.Carrito.Entity.Producto;
import com.informatorio.Carrito.Repository.ProductoRepository;
import com.informatorio.Carrito.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductoController {


    private ProductoRepository productoRepository;
    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoRepository productoRepository, ProductoService productoService) {
        this.productoRepository = productoRepository;
        this.productoService = productoService;
    }

    @PostMapping(value = "/producto")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping(value = "/producto")
    public ResponseEntity<?> getAllProducto(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "estado", required = false) String estado)
    {
        return ResponseEntity.ok(this.productoService.mostrarProductos(nombre,estado));

    }


    @GetMapping(value = "/producto/{id}")
    public Producto getProductoPorId(@PathVariable("id") Long id) {

        return productoRepository.findById(id).get();
    }

    @PutMapping(value = "/producto/{id}")
    public ResponseEntity<?> modificarporIdProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {

        try {
            return ResponseEntity.ok(this.productoService.modificarProducto(id, producto));
        }catch(Exception e){
            return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping(value = "/producto/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable("id") Long id) {

        try {
            productoRepository.deleteById(id);
            return ResponseEntity.ok("Borrado "+id);
        }catch(Exception e) {
                return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
            }

    }

}
