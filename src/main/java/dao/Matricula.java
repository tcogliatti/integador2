package dao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Matricula {
    @EmbeddedId
    private MatriculaId id = new MatriculaId();
    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;
    @ManyToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;
    @Column
    private Date inscripcion;
    @Column
    private boolean graduado;

    public Matricula() {
    }
    public Matricula(MatriculaId id, Carrera carrera, Estudiante estudiante, Date inscripcion, boolean graduado) {
        this.id = id;
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.inscripcion = inscripcion;
        this.graduado = graduado;
    }

    public MatriculaId getId() {
        return id;
    }

    public void setId(MatriculaId id) {
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

    public Date getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Date inscripcion) {
        this.inscripcion = inscripcion;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    public class MatriculaId implements Serializable {
        private int idEstudiante;
        private int idCarrera;
        public MatriculaId() {
        }
        public MatriculaId(int idEstudiante, int idCarrera) {
            this.idEstudiante = idEstudiante;
            this.idCarrera = idCarrera;
        }
        public int getIdEstudiante() {
            return idEstudiante;
        }
        public void setIdEstudiante(int idEstudiante) {
            this.idEstudiante = idEstudiante;
        }
        public int getIdCarrera() {
            return idCarrera;
        }
        public void setIdCarrera(int idCarrera) {
            this.idCarrera = idCarrera;
        }
    }
}
