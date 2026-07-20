package com.suarez.ropa.service;

import com.suarez.ropa.exception.CarritoNoEncontradoException;
import com.suarez.ropa.exception.StockInsuficienteException;
import com.suarez.ropa.model.Carrito;
import com.suarez.ropa.model.CarritoProducto;
import com.suarez.ropa.model.Producto;
import com.suarez.ropa.repository.CarritoProductoRepository;
import com.suarez.ropa.repository.CarritoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoService productoService;
    private final CarritoProductoRepository carritoProductoRepository;

    public CarritoService(CarritoRepository carritoRepository,
                          CarritoProductoRepository carritoProductoRepository,
                          ProductoService productoService) {
        this.carritoRepository = carritoRepository;
        this.productoService = productoService;
        this.carritoProductoRepository = carritoProductoRepository;                 
    }

    public Carrito crear() {
        return carritoRepository.save(new Carrito());
    }

    public Carrito obtenerPorId(Integer id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new CarritoNoEncontradoException(
                        "No se encontró un carrito con id " + id));
    }

    public List<Carrito> listarTodos() {
        return carritoRepository.findAll();
    }

    @Transactional
    public Carrito agregarProducto(Integer carritoId, Integer productoId) {
        Carrito carrito = obtenerPorId(carritoId);
        Producto producto = productoService.obtenerPorId(productoId);

        if (producto.getStock() <= 0) {
            throw new StockInsuficienteException(
                    "El producto \"" + producto.getNombre() + "\" no tiene stock disponible.");
        }

        Optional<CarritoProducto> existente = carritoProductoRepository.findByCarritoAndProducto(carrito, producto);

        if (existente.isPresent()) {
            CarritoProducto cp = existente.get();
            cp.setCantidad(cp.getCantidad() + 1);
            carritoProductoRepository.save(cp);
        } else {
            CarritoProducto nuevo = new CarritoProducto(null, carrito, producto, 1);
            carritoProductoRepository.save(nuevo);
        }

        producto.setStock(producto.getStock() - 1);
        productoService.guardar(producto);

        return carritoRepository.save(carrito);
    }

    public Carrito vaciar(Integer id) {
        Carrito carrito = obtenerPorId(id);
        carritoProductoRepository.deleteAll(carrito.getProductos());
        carrito.getProductos().clear();
        return carritoRepository.save(carrito);
    }

    public void eliminar(Integer id) {
        Carrito carrito = obtenerPorId(id);
        carritoRepository.delete(carrito);
    }
}
