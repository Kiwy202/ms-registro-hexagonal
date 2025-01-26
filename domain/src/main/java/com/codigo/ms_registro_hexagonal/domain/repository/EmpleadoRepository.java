package com.codigo.ms_registro_hexagonal.domain.repository;

import com.codigo.ms_registro_hexagonal.domain.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository {
    Empleado guardar(Empleado empleado);
    Optional<Empleado> buscarPorNumeroDocumento(String numeroDocumento);
    List<Empleado> listarTodos();
    void eliminar(String id);
}