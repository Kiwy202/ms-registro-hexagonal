package com.codigo.ms_registro_hexagonal.domain.repository;

import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByNumDoc(String numDoc);

    List<Empleado> findAllByEstadoTrue();
}