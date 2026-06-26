package com.domaco.venta_service.controller;

import com.domaco.venta_service.model.Venta;
import com.domaco.venta_service.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<?> listarVentas() {

        List<Venta> ventas = ventaService.listarVentas();

        if (ventas.isEmpty()) {

            return ResponseEntity.ok("No hay ventas registradas");
        }

        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<String> guardarVenta(@RequestBody Venta venta) {

        ventaService.guardarVenta(venta);

        return ResponseEntity.ok("Venta registrada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarVenta(
            @PathVariable Long id,
            @RequestBody Venta venta) {

        ventaService.actualizarVenta(id, venta);

        return ResponseEntity.ok("Venta actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id) {

        ventaService.eliminarVenta(id);

        return ResponseEntity.ok("Venta eliminada correctamente");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable Long id) {

        Venta venta = ventaService.obtenerVentaPorId(id);

        return ResponseEntity.ok(venta);
    }

}