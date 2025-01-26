package com.codigo.ms_registro_hexagonal.domain.model;

import org.junit.Test;

import java.sql.Timestamp;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmpleadoTest {

    @Test
    public void testEmpleadoCreation() {
        // Preparar datos
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Empleado empleado = new Empleado();
        empleado.setId(1L);
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleado.setEdad(30);
        empleado.setCargo("Analista");
        empleado.setTipoDoc("DNI");
        empleado.setNumDoc("987654321");
        empleado.setDepartamento("Sistemas");
        empleado.setSalario(3000.50);
        empleado.setTelefono("987654321");
        empleado.setCorreo("juan.perez@example.com");
        empleado.setEstado(true);
        empleado.setDireccion("Calle Falsa 123");
        empleado.setDateCrea(now);
        empleado.setUsuaCrea("admin");

        // Validaciones
        assertEquals("Juan", empleado.getNombre());
        assertEquals("Pérez", empleado.getApellido());
        assertEquals("DNI", empleado.getTipoDoc());
        assertEquals("987654321", empleado.getNumDoc());
        assertEquals(30, empleado.getEdad());
        assertEquals(3000.50, empleado.getSalario());
        assertTrue(empleado.isEstado());
        assertEquals("admin", empleado.getUsuaCrea());
    }
}
