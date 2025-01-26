package com.codigo.ms_registro_hexagonal.infraestructure.adapters.primary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void crearEmpleadoTest() throws Exception {
        Empleado empleado = new Empleado();
        empleado.setId(1L);
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleado.setEdad(30);
        empleado.setCargo("Analista");
        empleado.setTipoDoc("DNI");
        empleado.setNumDoc("12345678");
        empleado.setDepartamento("Recursos Humanos");
        empleado.setSalario(3500.00);
        empleado.setTelefono("987654321");
        empleado.setCorreo("juan.perez@example.com");
        empleado.setEstado(true);
        empleado.setDireccion("Av. Principal 123");
        empleado.setDateCrea(Timestamp.from(Instant.now()));
        empleado.setUsuaCrea("admin");

        mockMvc.perform(post("/empleados/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.cargo").value("Analista"))
                .andExpect(jsonPath("$.salario").value(3500.00));
    }
}
