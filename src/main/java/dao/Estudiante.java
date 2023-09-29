package dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

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
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
//    @Null
    private List<Matricula> inscripciones;

    public Estudiante() {
    }

    public Estudiante(int nro_libreta, int dni, String nombre, String apellido, Date nacimiento, char genero, String direccion) {
        this.nro_libreta = nro_libreta;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.inscripciones = null;
    }

    public Estudiante(int nro_libreta, int dni, String nombre, String apellido, Date nacimiento, char genero, String direccion, List<Matricula> inscripciones) {
        this.nro_libreta = nro_libreta;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.inscripciones = inscripciones;
    }

    public int getNro_libreta() {
        return nro_libreta;
    }

    public void setNro_libreta(int nro_libreta) {
        this.nro_libreta = nro_libreta;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Matricula> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Matricula> inscripciones) {
        this.inscripciones = inscripciones;
    }
}
