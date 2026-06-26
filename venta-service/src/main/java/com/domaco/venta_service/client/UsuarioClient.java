package com.domaco.venta_service.client;

import com.domaco.venta_service.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "usuario-service",
        url = "http://localhost:8084"
)
public interface UsuarioClient {

    @GetMapping("/usuarios/email/{email}")
    UsuarioDTO obtenerUsuarioPorEmail(
            @PathVariable String email);
}