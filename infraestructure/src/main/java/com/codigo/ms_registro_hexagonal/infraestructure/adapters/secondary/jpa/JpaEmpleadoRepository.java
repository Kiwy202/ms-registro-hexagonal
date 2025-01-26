package com.codigo.ms_registro_hexagonal.infraestructure.adapters.secondary.jpa;

import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaEmpleadoRepository extends JpaRepository<Empleado, String> {
    Optional<Empleado> findByNumeroDocumentoAndActivoTrue(String numeroDocumento);
}