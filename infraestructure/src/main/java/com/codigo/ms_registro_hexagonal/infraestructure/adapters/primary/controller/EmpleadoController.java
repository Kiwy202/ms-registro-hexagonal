package com.codigo.ms_registro_hexagonal.infraestructure.adapters.primary.controller;

import com.codigo.ms_registro_hexagonal.domain.dto.ReniecResponse;
import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import com.codigo.ms_registro_hexagonal.domain.service.EmpleadoService;
import com.codigo.ms_registro_hexagonal.domain.service.ReniecService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final ReniecService reniecService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@RequestParam String numDoc) {
        try {
            ReniecResponse reniecResponse = reniecService.buscarPorDni(numDoc);

            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNombre(reniecResponse.getNombres());
            nuevoEmpleado.setApellido(reniecResponse.getApellidoPaterno() + " " + reniecResponse.getApellidoMaterno());
            nuevoEmpleado.setNumDoc(numDoc);
            nuevoEmpleado.setEstado(true);

            Empleado empleadoGuardado = empleadoService.crearEmpleado(nuevoEmpleado);
            return ResponseEntity.ok(empleadoGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{numDoc}")
    public ResponseEntity<Empleado> buscarEmpleado(@PathVariable String numDoc) {
        return empleadoService.buscarPorNumeroDocumento(numDoc)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Empleado>> listarEmpleadosActivos() {
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(empleadoService.actualizarEmpleado(empleado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}