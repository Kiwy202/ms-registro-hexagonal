package com.codigo.ms_registro_hexagonal.infraestructure.adapters.primary.controller;

import com.codigo.ms_registro_hexagonal.domain.dto.ReniecResponse;
import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import com.codigo.ms_registro_hexagonal.domain.service.EmpleadoService;
import com.codigo.ms_registro_hexagonal.domain.service.ReniecService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final ReniecService reniecService;

    // Constructor con inyección de dependencias
    public EmpleadoController(EmpleadoService empleadoService, ReniecService reniecService) {
        this.empleadoService = empleadoService;
        this.reniecService = reniecService;
    }

    // Crear un empleado con datos autocompletados desde el API externo
    @PostMapping("/crear")
    public ResponseEntity<Empleado> crearEmpleado(@RequestParam String numeroDni) {
        // Consumir el API de RENIEC
        ReniecResponse reniecResponse = reniecService.buscarPorDni(numeroDni);

        // Crear el empleado con los datos obtenidos
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombres(reniecResponse.getNombres());
        nuevoEmpleado.setApellidos(reniecResponse.getApellidos());
        nuevoEmpleado.setActivo(true);

        // Guardar el empleado en la base de datos
        Empleado empleadoGuardado = empleadoService.crearEmpleado(nuevoEmpleado);
        return ResponseEntity.ok(empleadoGuardado);
    }

    // Buscar empleado por número de documento
    @GetMapping("/buscar/{numeroDocumento}")
    public ResponseEntity<Empleado> buscarEmpleado(@PathVariable String numeroDocumento) {
        return empleadoService.buscarPorNumeroDocumento(numeroDocumento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todos los empleados activos
    @GetMapping("/todos")
    public ResponseEntity<List<Empleado>> listarEmpleadosActivos() {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        return ResponseEntity.ok(empleados);
    }

    // Actualizar un empleado
    @PutMapping("/actualizar")
    public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado empleado) {
        Empleado actualizado = empleadoService.actualizarEmpleado(empleado);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un empleado (borrado lógico)
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable String id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}