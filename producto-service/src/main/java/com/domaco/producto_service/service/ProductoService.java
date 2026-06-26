package com.domaco.producto_service.service;

import com.domaco.producto_service.model.Producto;
import com.domaco.producto_service.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductoService {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductoService.class);

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {

        logger.info("Listando productos");

        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {

        logger.info("Guardando producto: {}", producto.getNombre());

        // Validar código repetido
        if (productoRepository.existsByCodigoProducto(
                producto.getCodigoProducto())) {

            logger.error("Código duplicado");

            throw new RuntimeException(
                    "Ya existe un producto con este código");
        }

        // Validar letras + números
        if(!producto.getCodigoProducto()
                .matches("^(?=.*[A-Za-z])(?=.*\\d).+$")){

            logger.error("Código inválido");

            throw new RuntimeException(
                    "El código debe contener letras y números");
        }

        // Validar stock
        if(producto.getStock() <= 0){

            logger.error("Stock inválido");

            throw new RuntimeException(
                    "El stock debe ser mayor a 0");
        }

        logger.info("Producto guardado correctamente");

        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {

        logger.info("Actualizando producto con ID: {}", id);

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        // Validar stock
        if(productoActualizado.getStock() <= 0){

            logger.error("Stock inválido");

            throw new RuntimeException(
                    "El stock debe ser mayor a 0");
        }

        producto.setNombre(
                productoActualizado.getNombre());

        producto.setPrecio(
                productoActualizado.getPrecio());

        producto.setStock(
                productoActualizado.getStock());

        producto.setCategoria(
                productoActualizado.getCategoria());

        producto.setMarca(
                productoActualizado.getMarca());

        producto.setCodigoProducto(
                productoActualizado.getCodigoProducto());

        logger.info("Producto actualizado correctamente");

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {

        logger.info("Eliminando producto con ID: {}", id);

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        productoRepository.delete(producto);

        logger.info("Producto eliminado correctamente");
    }

    public Producto obtenerProductoPorId(Long id) {

        logger.info("Buscando producto con ID: {}", id);

        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Producto con ID "
                                        + id
                                        + " no encontrado"));
    }

    public Producto obtenerProductoPorCodigo(String codigo) {

        logger.info("Buscando producto por código: {}", codigo);

        return productoRepository.findByCodigoProducto(codigo)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Producto no encontrado"));
    }
}