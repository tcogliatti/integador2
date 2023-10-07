package dao;

import entidades.Carrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CarreraRepository {
    private EntityManager  em;

    public CarreraRepository(EntityManager em){
        this.em = em;
    }

    /* f)
        recuperar las carreras con estudiantes inscriptos,
        y ordenar por cantidad de inscriptos.
     */

    public  List<Carrera> carrerasConInscriptos() {
        Query queryCarreras = em.createQuery(
                "SELECT c FROM Carrera c " +
                        "WHERE c.idCarrera IN (SELECT m.carrera.idCarrera FROM Matricula m) " +
                        "ORDER BY SIZE(c.matriculados) ASC");
        List<Carrera> carreras = queryCarreras.getResultList();
        return carreras;
    }
    //////////////////////////////////////// a) Dar de  ALTA un  CARRERA
    public  void altaCarrera( Carrera carrera){
        em.persist(carrera);
    }

    public  void altaCarrera(  int idCarrera, String nombre, int duracion){
        Carrera nuevaCarrera;
        nuevaCarrera = new Carrera( idCarrera, nombre, duracion);
        altaCarrera( nuevaCarrera);
    }

    public  Carrera obtenerCarreraPorNombre(String nombreCarrera){
        Query query = em.createQuery(
                " SELECT c  FROM Carrera c WHERE  c.nombre LIKE :carrera") ;
        query.setParameter("carrera", nombreCarrera);
        List<Carrera> resultado = query.getResultList();
        if (resultado.size() == 0){
            System.out.println("No se encuentra la carrera solicitada " +nombreCarrera );
            return null;
        }else{
            System.out.println(resultado.get(0));
            return resultado.get(0);
        }
    }
}
