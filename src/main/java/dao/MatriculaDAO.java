package dao;

import entidades.Matricula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class MatriculaDAO {

    public static List<Matricula> estudiantesPorCarreraAgrupadosPorCiudad(EntityManager em, String carrera, String ciudad) {
        Query query = em.createQuery(
                " SELECT m  FROM Matricula m WHERE  m.estudiante.direccion LIKE :ciudad AND m.carrera.nombre LIKE :carrera") ;
        query.setParameter("carrera", carrera);
        query.setParameter("ciudad", ciudad);
        List<Matricula> matriculas = query.getResultList();
        return matriculas;
    }
}
