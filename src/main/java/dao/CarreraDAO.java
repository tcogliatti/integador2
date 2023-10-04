package dao;

import entidades.Carrera;
import entidades.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CarreraDAO {

    /* f)
        recuperar las carreras con estudiantes inscriptos,
        y ordenar por cantidad de inscriptos.
     */

    public static List<Carrera> carrerasConInscriptos(EntityManager em) {
        Query queryCarreras = em.createQuery(
                "SELECT c FROM Carrera c " +
                        "WHERE c.idCarrera IN (SELECT m.carrera.idCarrera FROM Matricula m) " +
                        "ORDER BY SIZE(c.matriculados) ASC");
        List<Carrera> carreras = queryCarreras.getResultList();
        return carreras;
    }
    //////////////////////////////////////// a) Dar de  ALTA un  CARRERA
    public static void altaCarrera(EntityManager em, Carrera carrera){
        em.persist(carrera);
    }

    public static void altaCarrera( EntityManager em, int idCarrera, String nombre, int duracion){
        Carrera nuevaCarrera;
        nuevaCarrera = new Carrera( idCarrera, nombre, duracion);
        altaCarrera( em,nuevaCarrera);
    }
}
