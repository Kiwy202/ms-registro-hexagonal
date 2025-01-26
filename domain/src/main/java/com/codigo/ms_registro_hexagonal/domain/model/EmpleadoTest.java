package com.codigo.ms_registro_hexagonal.domain.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EmpleadoTest {

    @Test
    public void testEmpleadoCreation() {
        Empleado empleado = new Empleado(
                "123",
                "Juan",
                "Pérez",
                "DNI",
                "987654321",
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        assertEquals("Juan", empleado.getNombres());
        assertEquals("Pérez", empleado.getApellidos());
        assertTrue(empleado.isActivo());
    }
}
