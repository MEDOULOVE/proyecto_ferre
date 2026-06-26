package com.domaco.venta_service.client;

import com.domaco.venta_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "producto-service",
        url = "${PRODUCTO_SERVICE_URL}"
)
public interface ProductoClient {

    @GetMapping("/productos/codigo/{codigo}")
    ProductoDTO obtenerProductoPorCodigo(
            @PathVariable String codigo);

    @PutMapping("/productos/{id}")
    ProductoDTO actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoDTO producto);
}