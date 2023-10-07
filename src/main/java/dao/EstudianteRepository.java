package dao;

import entidades.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class EstudianteRepository {
    //////////////////////////////////////// a) Dar de  ALTA un  ESTUDIANTE
    public static void cargarEstudiante(EntityManager em, Estudiante estudiante){
        em.persist(estudiante);
    }

    public static void cargarEstudiante( EntityManager em, int nro_libreta, int dni, String nombre, String apellido, int edad, String genero, String direccion){
        Estudiante est;
        est = new Estudiante(nro_libreta, dni, nombre, apellido, edad, genero, direccion);
        cargarEstudiante( em,est);
    }


    ////////////////////////(c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    public static List<Estudiante> obtenerTodosLosEstudiantes(EntityManager em) {
        Query queryEstudiante = em.createNamedQuery(Estudiante.OBTENER_TODOS);
        List<Estudiante> estudiantes = queryEstudiante.getResultList();
        return estudiantes;
    }

    ///////////////////////////////////////////////////////////// (d)  recuperar un estudiante, en base a su número de libreta universitaria.

    public static Estudiante buscarEstudiantePorLibretaUniversitaria(EntityManager em, int lu) {
        Query busquedaPorLU = em.createQuery("SELECT e FROM Estudiante e WHERE e.lu = :lu");
        busquedaPorLU.setParameter("lu", lu);
        List<Estudiante> estudiantes = busquedaPorLU.getResultList();
        if(estudiantes.isEmpty()){
            return null;
        }else{
            return estudiantes.get(0);

        }
    }

    //////////////////////////////////////////////////////////////  /* (e) recuperar todos los estudiantes, en base a su género. OTRA VERSIÓN

    public static List<Estudiante>  obtenerEstudiantesPorGenero(EntityManager em, String generoBuscado) {
        Query queryEstudiante = em.createQuery("SELECT e FROM Estudiante e  WHERE e.genero LIKE  :genero ");
        queryEstudiante.setParameter("genero", generoBuscado);
        List<Estudiante> estudiantes = queryEstudiante.getResultList();
        return estudiantes;
    }
}
