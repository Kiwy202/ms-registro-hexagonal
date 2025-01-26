package com.codigo.ms_registro_hexagonal.domain.service;

import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import com.codigo.ms_registro_hexagonal.domain.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public Empleado crearEmpleado(Empleado empleado) {
        empleado.setEstado(true);
        empleado.setDateCrea(new Timestamp(System.currentTimeMillis()));
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> buscarPorNumeroDocumento(String numDoc) {
        return empleadoRepository.findByNumDoc(numDoc);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAllByEstadoTrue();
    }

    public Empleado actualizarEmpleado(Empleado empleado) {
        Empleado existente = empleadoRepository.findById(empleado.getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        existente.setNombre(empleado.getNombre());
        existente.setApellido(empleado.getApellido());
        existente.setEdad(empleado.getEdad());
        existente.setCargo(empleado.getCargo());
        existente.setTipoDoc(empleado.getTipoDoc());
        existente.setNumDoc(empleado.getNumDoc());
        existente.setDepartamento(empleado.getDepartamento());
        existente.setSalario(empleado.getSalario());
        existente.setTelefono(empleado.getTelefono());
        existente.setCorreo(empleado.getCorreo());
        existente.setDireccion(empleado.getDireccion());
        existente.setDateUdpate(new Timestamp(System.currentTimeMillis()));
        return empleadoRepository.save(existente);
    }

    public void eliminarEmpleado(Long id) {
        Empleado existente = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        existente.setEstado(false);
        existente.setDateDelete(new Timestamp(System.currentTimeMillis()));
        empleadoRepository.save(existente);
    }
}