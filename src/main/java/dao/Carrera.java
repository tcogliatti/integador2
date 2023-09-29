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
    @Column
    private int materias;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "carrera")
    private List<Matricula> matriculados;
    public Carrera() {
    }
    public Carrera(int idCarrera, String nombre, int duracion, int materias, List<Matricula> matriculados) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.duracion = duracion;
        this.materias = materias;
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

    public int getMaterias() {
        return materias;
    }

    public void setMaterias(int materias) {
        this.materias = materias;
    }

    public List<Matricula> getMatriculados() {
        return matriculados;
    }

    public void setMatriculados(List<Matricula> matriculados) {
        this.matriculados = matriculados;
    }
}
