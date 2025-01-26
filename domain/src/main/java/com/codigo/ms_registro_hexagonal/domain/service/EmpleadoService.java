package com.codigo.ms_registro_hexagonal.domain.service;

import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import com.codigo.ms_registro_hexagonal.domain.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado crearEmpleado(Empleado empleado) {
        empleado.setId(java.util.UUID.randomUUID().toString());
        empleado.setFechaCreacion(LocalDateTime.now());
        empleado.setActivo(true);
        return empleadoRepository.guardar(empleado);
    }

    public Optional<Empleado> buscarPorNumeroDocumento(String numeroDocumento) {
        return empleadoRepository.buscarPorNumeroDocumento(numeroDocumento);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.listarTodos();
    }

    public Empleado actualizarEmpleado(Empleado empleado) {
        empleado.setFechaActualizacion(LocalDateTime.now());
        return empleadoRepository.guardar(empleado);
    }

    public void eliminarEmpleado(String id) {
        empleadoRepository.eliminar(id);
    }
}