package Repository;

import dto.CarreraCiudadDTO;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class MatriculaRepository {

    public static List<CarreraCiudadDTO> estudiantesPorCarreraAgrupadosPorCiudad(EntityManager em, String carrera, String ciudad) {
        Query query = em.createQuery(
                " SELECT new dto.CarreraCiudadDTO(m.carrera.nombre,m.estudiante.nombre,m.estudiante.apellido,m.estudiante.direccion)  FROM Matricula m WHERE  m.estudiante.direccion LIKE :ciudad AND m.carrera.nombre LIKE :carrera") ;
        query.setParameter("carrera", carrera);
        query.setParameter("ciudad", ciudad);
        List<CarreraCiudadDTO> carrerasxCiudad = query.getResultList();
        return carrerasxCiudad;
    }
    public static void matricularEstudianteEnCarrera(EntityManager em,int id, Carrera carrera, Estudiante estudiante, int inscripcion, int graduado, int antiguedad){
        Matricula matricular;
        matricular = new Matricula( id , carrera, estudiante, inscripcion, graduado, antiguedad);
        matricularEstudianteEnCarrera(em,matricular);
    }
    public static void matricularEstudianteEnCarrera(EntityManager em,Matricula m){

        em.persist(m);
    }
}
