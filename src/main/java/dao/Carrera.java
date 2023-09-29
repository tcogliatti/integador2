package dao;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Carrera {
    @Id
    private int idCarrera;
    @Column
    private String nombre;
    @Column
    private int duracion;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carrera")
    private List<Matricula> matriculados;
    public Carrera() {
    }
    public Carrera(int idCarrera, String nombre, int duracion) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.duracion = duracion;
    }
    public Carrera(int idCarrera, String nombre, int duracion, List<Matricula> matriculados) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.duracion = duracion;
        this.matriculados = matriculados;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<Matricula> getMatriculados() {
        return matriculados;
    }

    public void setMatriculados(List<Matricula> matriculados) {
        this.matriculados = matriculados;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", matriculados=" + matriculados +
                '}';
    }
}
