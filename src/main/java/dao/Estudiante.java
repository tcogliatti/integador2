package dao;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Estudiante {
    @Id
    private int dni;
    @Column
    private int lu;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String direccion;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
//    @Null
    private List<Matricula> inscripciones;

    public Estudiante() {
    }

    public Estudiante(int nro_libreta, int dni, String nombre, String apellido, int nacimiento, String genero, String direccion) {
        this.lu = nro_libreta;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = nacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.inscripciones = null;
    }

    public Estudiante(int nro_libreta, int dni, String nombre, String apellido, int nacimiento, String genero, String direccion, List<Matricula> inscripciones) {
        this.lu = nro_libreta;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = nacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.inscripciones = inscripciones;
    }

    public int getLu() {
        return lu;
    }

    public void setLu(int nro_libreta) {
        this.lu = nro_libreta;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int nacimiento) {
        this.edad = nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
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

    @Override
    public String toString() {
        return "Estudiante{" +
                "lu=" + lu +
                ", dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", direccion='" + direccion + '\'' +
                ", inscripciones=" + inscripciones +
                '}';
    }
}
