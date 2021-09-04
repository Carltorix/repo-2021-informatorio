package com.informatorio.Carrito.Controller;

import com.informatorio.Carrito.Dto.CreateCarrito;
import com.informatorio.Carrito.Dto.OperacionCarrito;
import com.informatorio.Carrito.Entity.Carrito;
import com.informatorio.Carrito.Entity.LineaCarrito;
import com.informatorio.Carrito.Entity.Producto;
import com.informatorio.Carrito.Entity.Usuario;
import com.informatorio.Carrito.Repository.CarritoRepository;
import com.informatorio.Carrito.Repository.LineaCarritoRepository;
import com.informatorio.Carrito.Repository.ProductoRepository;
import com.informatorio.Carrito.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CarritoController {


    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private LineaCarritoRepository lineaCarritoRepository;

    @Autowired
    public CarritoController(CarritoRepository carritoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository, LineaCarritoRepository lineaCarritoRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.lineaCarritoRepository = lineaCarritoRepository;
    }

    @PostMapping(value = "usuario/{id}/carrito")
    public ResponseEntity<?> createCarrito(@PathVariable("id") Long id,
                                           @Valid @RequestBody CreateCarrito createCarrito) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        Carrito carrito = new Carrito(createCarrito.getDevice());
        usuario.agregarCarrito(carrito);
        return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);
    }

    @GetMapping(value = "/carrito/{id}")
    public ResponseEntity<?> getCarritoPorId(@PathVariable("id") Long id) {
        var carrito = carritoRepository.findById(id).orElse(null);
        return (carrito != null) ?
                ResponseEntity.ok(carrito)
                : new ResponseEntity<>(
                "Carrito not found",
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping(value = "/carrito")

    public ResponseEntity<?> getAll(@RequestParam(value = "estado", required = false) String estado) {
        if(estado != null){
            if(estado.equalsIgnoreCase("false")){
                return new ResponseEntity(carritoRepository.findByCierreFalse(), HttpStatus.OK);
            }else{
                return new ResponseEntity(carritoRepository.findByCierreTrue(), HttpStatus.OK);
            }
        }
        return new ResponseEntity(carritoRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/carrito/{id}")
    public void borrarPorId(@PathVariable("id") Long id) {
        carritoRepository.deleteById(id);
    }

    @PutMapping(value = "usuario/{id}/carrito/{idCarrito}")
    public ResponseEntity<?> agregarProducto(@PathVariable("id") Long id,
                                             @PathVariable("idCarrito") Long idCarrito,
                                             @RequestBody OperacionCarrito operacionCarrito) {

        Producto producto = productoRepository.findById(operacionCarrito.getProductoId()).orElse(null);
        if(producto == null){
            return new ResponseEntity<>("El producto no Existe",HttpStatus.NOT_FOUND);
        }
        if(producto.getPublicado()) {
            Carrito carritoE = carritoRepository.findById(idCarrito).orElse(null);
            if(carritoE == null){
                this.createCarrito(id,new CreateCarrito("Computadora"));
            }
            LineaCarrito lineaCarrito = new LineaCarrito();
            if (carritoE.getLineaDeCarritos().size() > 0) {
                for (LineaCarrito lC : carritoE.getLineaDeCarritos()) {
                    if (lC.getProducto().getId() == operacionCarrito.getProductoId()) {
                        lineaCarrito = lC;
                    }
                }
            }
            lineaCarrito.setProducto(producto);
            lineaCarrito.setCantidad(operacionCarrito.getCantidad());
            carritoE.agregarLineaCarrito(lineaCarrito);
            return  ResponseEntity.ok(carritoRepository.save(carritoE));
        }else{
            return new ResponseEntity<>("El producto no esta publicado",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/usuario/{id}/carrito/{idCarrito}/producto/{idProducto}")
    public ResponseEntity<?> borrarProducto(@PathVariable("id") Long userId,
                               @PathVariable("idCarrito") Long idCarrito,
                               @PathVariable("idProducto") Long idProducto) {
        Long linea = lineaCarritoRepository.findByProducto(productoRepository.findById(idProducto).get()).getId();
        lineaCarritoRepository.deleteById(linea);
        return ResponseEntity.ok("El producto se Borro");
        //return new ResponseEntity<>("El producto se Borro",HttpStatus.OK);
    }
}
