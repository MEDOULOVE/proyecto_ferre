package com.domaco.venta_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String categoria;
    private String marca;
    private String codigoProducto;
}