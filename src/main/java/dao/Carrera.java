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
    @OneToMany(fetch = FetchType.LAZY)
    private List<Estudiante> estudiantes;
}
