package com.codigo.ms_registro_hexagonal.domain.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.sql.Timestamp;



@Entity
@Data
public class Empleado {
    @Id
    private Long id;

    private String nombre;
    private String apellido;
    private int edad;
    private String cargo;
    private String tipoDoc;
    private String numDoc;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private boolean estado;
    private String direccion;
    private Timestamp dateCrea;
    private String usuaCrea;
    private Timestamp dateUdpate;
    private String usuaUpdate;
    private Timestamp dateDelete;
    private String usuaDelete;
}