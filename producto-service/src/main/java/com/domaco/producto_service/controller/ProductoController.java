package com.domaco.producto_service.controller;

import com.domaco.producto_service.dto.ProductoDTO;
import com.domaco.producto_service.model.Producto;
import com.domaco.producto_service.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<?> listarProductos() {

        List<Producto> productos = productoService.listarProductos();

        if (productos.isEmpty()) {
            return ResponseEntity.ok("No hay productos registrados");
        }

        return ResponseEntity.ok(productos);
    }

    @PostMapping("/lista")
    public ResponseEntity<String> guardarLista(
            @RequestBody List<Producto> productos) {

        productos.forEach(productoService::guardarProducto);

        return ResponseEntity.ok("Productos agregados correctamente");
    }

    @PostMapping
    public ResponseEntity<String> guardarProducto(
            @Valid @RequestBody ProductoDTO productoDTO) {

        Producto producto = new Producto();

        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setMarca(productoDTO.getMarca());
        producto.setCodigoProducto(productoDTO.getCodigoProducto());

        productoService.guardarProducto(producto);

        return ResponseEntity.ok("Producto agregado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto producto) {

        Producto productoActualizado =
                productoService.actualizarProducto(
                        id,
                        producto);

        return ResponseEntity.ok(
                productoActualizado);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> obtenerPorCodigo(
            @PathVariable String codigo) {

        Producto producto =
                productoService.obtenerProductoPorCodigo(codigo);

        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable Long id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.ok("Producto eliminado correctamente");
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(
            @PathVariable Long id) {

        return productoService.obtenerProductoPorId(id);
    }
}