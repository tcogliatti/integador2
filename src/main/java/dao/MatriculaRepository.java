package dao;

import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class MatriculaRepository {

    private EntityManager  em;

    public MatriculaRepository(EntityManager em){
        this.em = em;
    }
    public
    List<Matricula> estudiantesPorCarreraAgrupadosPorCiudad( String carrera, String ciudad) {
        Query query = em.createQuery(
                " SELECT m  FROM Matricula m WHERE  m.estudiante.direccion LIKE :ciudad AND m.carrera.nombre LIKE :carrera") ;
        query.setParameter("carrera", carrera);
        query.setParameter("ciudad", ciudad);
        List<Matricula> matriculas = query.getResultList();
        return matriculas;
    }

    public void matricularEstudianteEnCarrera( int id, Carrera carrera, Estudiante estudiante, int inscripcion, int graduado, int antiguedad){
        Matricula matricular;
        matricular = new Matricula( id , carrera, estudiante, inscripcion, graduado, antiguedad);
        em.persist(matricular);
    }
}
