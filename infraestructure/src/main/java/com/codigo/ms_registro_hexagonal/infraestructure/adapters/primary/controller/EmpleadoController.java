package com.codigo.ms_registro_hexagonal.infraestructure.adapters.primary.controller;

import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import com.codigo.ms_registro_hexagonal.domain.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    @GetMapping("/buscar/{numeroDocumento}")
    public ResponseEntity<Empleado> buscarEmpleado(@PathVariable String numeroDocumento) {
        return empleadoService.buscarPorNumeroDocumento(numeroDocumento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Empleado>> listarEmpleadosActivos() {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado empleado) {
        Empleado actualizado = empleadoService.actualizarEmpleado(empleado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable String id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}