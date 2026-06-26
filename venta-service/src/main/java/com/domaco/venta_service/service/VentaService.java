package com.domaco.venta_service.service;

import com.domaco.venta_service.client.ProductoClient;

import com.domaco.venta_service.dto.ProductoDTO;

import com.domaco.venta_service.model.Venta;
import com.domaco.venta_service.repository.VentaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VentaService {

    private static final Logger logger =
            LoggerFactory.getLogger(VentaService.class);

    private final VentaRepository ventaRepository;
    private final ProductoClient productoClient;

    private final EmailService emailService;

    public VentaService(
            VentaRepository ventaRepository,
            ProductoClient productoClient,

            EmailService emailService) {

        this.ventaRepository = ventaRepository;
        this.productoClient = productoClient;

        this.emailService = emailService;
    }

    // ===================== LISTAR =====================
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    // ===================== GUARDAR VENTA =====================
    public Venta guardarVenta(Venta venta) {

        logger.info("Registrando venta para cliente: {}", venta.getCliente());

        ProductoDTO producto =
                productoClient.obtenerProductoPorCodigo(venta.getProductoId());

        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        if (producto.getStock() <= 0) {
            throw new RuntimeException("No hay stock disponible");
        }

        if (venta.getCantidad() > producto.getStock()) {
            throw new RuntimeException(
                    "Stock insuficiente. Disponible: " + producto.getStock());
        }

        // descontar stock
        producto.setStock(producto.getStock() - venta.getCantidad());
        productoClient.actualizarProducto(producto.getId(), producto);

        // datos venta
        venta.setProductoId(producto.getCodigoProducto());
        venta.setProductoNombre(producto.getNombre());

        Double total = producto.getPrecio() * venta.getCantidad();
        venta.setTotal(total);

        // guardar venta
        Venta ventaGuardada = ventaRepository.save(venta);



        // ENVIAR EMAIL (SOLO ESTO IMPORTA)
        emailService.enviarBoleta(venta.getEmail(), venta);

        return ventaGuardada;
    }

    // ===================== ACTUALIZAR =====================
    public Venta actualizarVenta(Long id, Venta ventaActualizada) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.setCliente(ventaActualizada.getCliente());
        venta.setCantidad(ventaActualizada.getCantidad());

        return ventaRepository.save(venta);
    }



    // ===================== ELIMINAR =====================
    public void eliminarVenta(Long id) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        ventaRepository.delete(venta);
    }

    // ===================== BUSCAR =====================
    public Venta obtenerVentaPorId(Long id) {

        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }
}