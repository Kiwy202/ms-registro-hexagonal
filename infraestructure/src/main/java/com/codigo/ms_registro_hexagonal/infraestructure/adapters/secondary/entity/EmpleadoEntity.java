package com.codigo.ms_registro_hexagonal.infraestructure.adapters.secondary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "empleado")
public class EmpleadoEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String tipoDocumento;

    @Column(nullable = false, unique = true)
    private String numeroDocumento;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

}