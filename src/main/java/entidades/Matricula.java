package entidades;

import jakarta.persistence.*;

@Entity
public class Matricula {
    @Id
    private int id;
    @ManyToOne
    private Carrera carrera;
    @ManyToOne
    private Estudiante estudiante;
    @Column
    private int inscripcion;
    @Column
    private int graduacion;
    @Column
    private int antiguedad;

    public Matricula() {
    }
    public Matricula(int id, Carrera carrera, Estudiante estudiante, int inscripcion, int graduado, int antiguedad) {
        this.id = id;
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.inscripcion = inscripcion;
        this.graduacion = graduado;
        this.antiguedad = antiguedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public int getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(int inscripcion) {
        this.inscripcion = inscripcion;
    }

    public int getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(int graduado) {
        this.graduacion = graduado;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", carrera=" + carrera +
                ", estudiante=" + estudiante +
                ", inscripcion=" + inscripcion +
                ", graduado=" + graduacion +
                '}';
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
