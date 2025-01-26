package com.codigo.ms_registro_hexagonal.infraestructure.adapters.secondary.jpa;

import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import com.codigo.ms_registro_hexagonal.domain.repository.EmpleadoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    private final List<Empleado> empleados = new ArrayList<>(); // Simulación de base de datos

    @Override
    public Empleado guardar(Empleado empleado) {
        empleados.add(empleado);
        return empleado;
    }

    @Override
    public Optional<Empleado> buscarPorNumeroDocumento(String numeroDocumento) {
        return empleados.stream()
                .filter(emp -> emp.getNumeroDocumento().equals(numeroDocumento) && emp.isActivo())
                .findFirst();
    }

    @Override
    public List<Empleado> listarTodos() {
        return empleados.stream()
                .filter(Empleado::isActivo)
                .toList();
    }

    @Override
    public void eliminar(String id) {
        empleados.stream()
                .filter(emp -> emp.getId().equals(id))
                .forEach(emp -> emp.setActivo(false));
    }
}