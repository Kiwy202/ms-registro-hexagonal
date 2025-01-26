package com.codigo.ms_registro_hexagonal.infraestructure.adapters.primary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.codigo.ms_registro_hexagonal.domain.model.Empleado;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

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
        Empleado empleado = new Empleado(
                "1",
                "Juan",
                "Pérez",
                "DNI",
                "12345678",
                true,
                LocalDateTime.now(),
                null
        );

        mockMvc.perform(post("/empleados/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Juan"));
    }
    
}