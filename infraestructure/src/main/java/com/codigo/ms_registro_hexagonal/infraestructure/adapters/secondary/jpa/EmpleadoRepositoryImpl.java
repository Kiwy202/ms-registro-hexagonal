package com.codigo.ms_registro_hexagonal.infraestructure.adapters.secondary.jpa;

import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import com.codigo.ms_registro_hexagonal.domain.repository.EmpleadoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadoRepositoryImpl {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoRepositoryImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> buscarPorNumeroDocumento(String numeroDocumento) {
        return empleadoRepository.findByNumDoc(numeroDocumento);
    }

    public List<Empleado> listarTodos() {
        return empleadoRepository.findAllByEstadoTrue();
    }

    public void eliminar(Long id) {
        empleadoRepository.findById(id).ifPresent(empleado -> {
            empleado.setEstado(false);
            empleadoRepository.save(empleado);
        });
    }
}