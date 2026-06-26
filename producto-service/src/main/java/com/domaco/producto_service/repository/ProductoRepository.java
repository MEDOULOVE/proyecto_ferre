package com.domaco.producto_service.repository;

import com.domaco.producto_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsByCodigoProducto(String codigoProducto);

    Optional<Producto> findByCodigoProducto(String codigoProducto);

}