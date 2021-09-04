package com.informatorio.Carrito.Service;

import com.informatorio.Carrito.Dto.IdCarrito;
import com.informatorio.Carrito.Entity.Carrito;
import com.informatorio.Carrito.Entity.LineaCarrito;
import com.informatorio.Carrito.Entity.LineaDetalle;
import com.informatorio.Carrito.Entity.Orden;
import com.informatorio.Carrito.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {

    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private LineaCarritoRepository lineaCarritoRepository;
    private OrdenRepository ordenRepository;

    @Autowired
    public OrdenService(CarritoRepository carritoRepository, UsuarioRepository usuarioRepository,
                        ProductoRepository productoRepository, LineaCarritoRepository lineaCarritoRepository,
                        OrdenRepository ordenRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.lineaCarritoRepository = lineaCarritoRepository;
        this.ordenRepository = ordenRepository;
    }

    public ResponseEntity<?> crearCargarOrden(IdCarrito idCarrito){
        if (idCarrito != null) {
            try {
                Carrito carrito = this.carritoRepository.findById(idCarrito.getId()).orElse(null);
                if (carrito != null) {
                    if (!carrito.getCierre()) {
                        if(carrito.getLineaDeCarritos().size()>0){
                            Orden orden = new Orden();
                            orden.setObservacion(idCarrito.getObservacion());
                            orden.setUsuario(carrito.getUsuario());
                            orden.setCarrito(carrito);
                            for (LineaCarrito lineaCarrito : carrito.getLineaDeCarritos()) {
                                LineaDetalle lineaDetalle = new LineaDetalle();
                                lineaDetalle.setCantidad(lineaCarrito.getCantidad());
                                lineaDetalle.setProducto(lineaCarrito.getProducto());
                                lineaDetalle.setPrecioUnitario(lineaCarrito.getProducto().getPrecioUnitario());
                                orden.agregarLineaDetalle(lineaDetalle);
                            }
                            carrito.setCierre(true);
                            return ResponseEntity.ok(this.ordenRepository.save(orden));
                        }else{
                            return new ResponseEntity<>("Carrito  sin linea", HttpStatus.NOT_FOUND);
                        }
                    }else{
                        return new ResponseEntity<>("Carrito cerrado", HttpStatus.NOT_FOUND);
                    }
                }
            }catch(Exception e){
                return new ResponseEntity<>("Carrito no existe", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Carrito not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> mostrarOrdenes(String estado){
        if(estado != null){
            if(estado.equalsIgnoreCase("true")){
                return new ResponseEntity(ordenRepository.findByBorrarTrue(), HttpStatus.OK);
            }
            return new ResponseEntity(ordenRepository.findByBorrarFalse(), HttpStatus.OK);
        }
        return new ResponseEntity(ordenRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> mostrarOrdenIdUser(Long id){

        var usuario =  usuarioRepository.findById(id).orElse(null);
        if(usuario != null) {
            var orden = ordenRepository.findByUsuario(usuario);

            return (orden.size() > 0) ? ResponseEntity.ok(orden) : new ResponseEntity<>("no tiene orden",
                    HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>("no tiene orden", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> borrarOrdenId(Long id){
        var orden = ordenRepository.findById(id).orElse(null);
        if(orden != null){
            orden.setBorrar(true);
        }
        return (orden != null) ?
                ResponseEntity.ok("Se borro la orden N° "+orden.getId())
                : new ResponseEntity<>(
                "user not found",
                HttpStatus.NOT_FOUND
        );
    }
}
