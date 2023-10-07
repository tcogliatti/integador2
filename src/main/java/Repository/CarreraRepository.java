package Repository;

import dto.CarreraDTO;
import entidades.Carrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CarreraRepository {

    /* f)
        recuperar las carreras con estudiantes inscriptos,
        y ordenar por cantidad de inscriptos.
     */

    public static List<CarreraDTO> carrerasConInscriptos(EntityManager em) {
        Query queryCarreras = em.createQuery(
                "SELECT new dto.CarreraDTO(c.nombre,SIZE(c.matriculados)) FROM Carrera c " +
                        "WHERE c.idCarrera IN (SELECT m.carrera.idCarrera FROM Matricula m) " +
                        "ORDER BY SIZE(c.matriculados) ASC");
        List<CarreraDTO> carreras = queryCarreras.getResultList();
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
    public static CarreraDTO obtenerCarreraPorNombre(EntityManager em,String nombreCarrera){
        Query query = em.createQuery(
                " SELECT new dto.CarreraDTO(c.)  FROM Carrera c WHERE  c.nombre LIKE :carrera") ;
        query.setParameter("carrera", nombreCarrera);
        List<CarreraDTO> resultado = query.getResultList();
        if (resultado.size() == 0){
            System.out.println("No se encuentra la carrera solicitada " +nombreCarrera );
            return null;
        }else{
            System.out.println(resultado.get(0));
            return resultado.get(0);
        }
    }
}
