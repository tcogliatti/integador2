package dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Estudiante {
    @Id
    private int nro_libreta;
    @Column
    private int dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Date nacimiento;
    @Column
    private char genero;
    @Column
    private String direccion;
    @OneToMany
    private List<Carrera> carreras;

}
