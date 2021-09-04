package com.informatorio.Carrito.Service;

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
import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private LineaCarritoRepository lineaCarritoRepository;

    @Autowired
    public CarritoService(CarritoRepository carritoRepository, UsuarioRepository usuarioRepository,
                          ProductoRepository productoRepository,
                          LineaCarritoRepository lineaCarritoRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.lineaCarritoRepository = lineaCarritoRepository;
    }

    public ResponseEntity<?> mostrarCarritos(String estado){
        if(estado != null){
            if(estado.equalsIgnoreCase("false")){
                return new ResponseEntity(carritoRepository.findByCierreFalse(), HttpStatus.OK);
            }else{
                return new ResponseEntity(carritoRepository.findByCierreTrue(), HttpStatus.OK);
            }
        }
        return new ResponseEntity(carritoRepository.findAll(), HttpStatus.OK);
    }

    public Carrito crearCar(Long id,String device){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        Carrito carrito = new Carrito(device);
        usuario.agregarCarrito(carrito);
        return carritoRepository.save(carrito);
    }

    public ResponseEntity<?> agregarProducto(Long id,Long idCarrito,OperacionCarrito operacionCarrito){
        try {
            Producto producto = productoRepository.findById(operacionCarrito.getProductoId()).orElse(null);
            if (producto == null) {
                return new ResponseEntity<>("El producto no Existe", HttpStatus.NOT_FOUND);
            }
            if (producto.getPublicado()) {
                Carrito carritoE = carritoRepository.findById(idCarrito).orElse(null);
                if (carritoE == null) {
                    this.crearCar(id, "computadora");
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
                return ResponseEntity.ok(carritoRepository.save(carritoE));
            } else {
                return new ResponseEntity<>("El producto no esta publicado", HttpStatus.NOT_ACCEPTABLE);
            }
        }catch(Exception e){
            return new ResponseEntity<>("No encontro Carrito",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> borrarLineaCarritoProducto(Long idProducto){
        try {
            Long linea = this.lineaCarritoRepository.findByProducto(this.productoRepository.findById(idProducto).get()).getId();
            this.lineaCarritoRepository.deleteById(linea);
            return ResponseEntity.ok("El producto se Borro");
        }catch(Exception e){
            return new ResponseEntity<>("El carrito no tiene ese producto",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> mostrarCarrito(Long id) {
        var carrito = carritoRepository.findById(id).orElse(null);
        return (carrito != null) ?
                ResponseEntity.ok(carrito)
                : new ResponseEntity<>(
                "Carrito no encontrado",
                HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> borrarCarrito(Long id) {
        try {
            this.carritoRepository.deleteById(id);
            return ResponseEntity.ok("Carrito "+ id+" Borrado");
        }catch (Exception e){
            return new ResponseEntity<>("El carrito no existe",HttpStatus.NOT_FOUND);
        }
    }
}
