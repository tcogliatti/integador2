package dao;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Matricula {
    @EmbeddedId
    private MatriculaId id = new MatriculaId();
    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;
    @OneToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;
    @Column
    private Date inscripcion;
    @Column
    private boolean graduado;
}
