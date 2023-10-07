package Repository;

import dto.EstudianteDTO;
import entidades.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


import java.util.List;


public class EstudianteRepository {


    //////////////////////////////////////// a) Dar de  ALTA un  ESTUDIANTE
    public void cargarEstudiante(EntityManager em, Estudiante estudiante) {
        try {
            em.persist(estudiante);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        em.close();
        }
    }
        public void cargarEstudiante (EntityManager em,int nro_libreta, int dni, String nombre, String apellido,
        int edad, String genero, String direccion){
            Estudiante est;
            est = new Estudiante(nro_libreta, dni, nombre, apellido, edad, genero, direccion);
            cargarEstudiante(em, est);
        }


        ////////////////////////(c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.<<< HEAD:src/main/java/dao/EstudianteDAO.java
        public static List<EstudianteDTO> obtenerTodosLosEstudiantes (EntityManager em){
            Query queryEstudiante = em.createQuery("SELECT new dto.EstudianteDTO(e.dni,e.lu,e.nombre,e.apellido,e.edad,e.genero,e.direccion) FROM Estudiante e");
            List<EstudianteDTO> estudiantes = queryEstudiante.getResultList();
            return estudiantes;
        }

        ///////////////////////////////////////////////////////////// (d)  recuperar un estudiante, en base a su número de libreta universitaria.

        public static EstudianteDTO buscarEstudiantePorLibretaUniversitaria (EntityManager em,int lu){
            Query busquedaPorLU = em.createQuery("SELECT new dto.EstudianteDTO(e.dni,e.lu,e.nombre,e.apellido,e.edad,e.genero,e.direccion) FROM Estudiante e WHERE e.lu = :lu");
            busquedaPorLU.setParameter("lu", lu);
            List<EstudianteDTO> estudiantes = busquedaPorLU.getResultList();
            if (estudiantes.isEmpty()) {
                return null;
            } else {
                return estudiantes.get(0);

            }
        }

        //////////////////////////////////////////////////////////////  /* (e) recuperar todos los estudiantes, en base a su género. OTRA VERSIÓN
        public static List<EstudianteDTO> obtenerEstudiantesPorGenero (EntityManager em, String generoBuscado){
            Query queryEstudiante = em.createQuery("SELECT new dto.EstudianteDTO(e.dni,e.lu,e.nombre,e.apellido,e.edad,e.genero,e.direccion) FROM Estudiante e  WHERE e.genero LIKE  :genero ");
            queryEstudiante.setParameter("genero", generoBuscado);
            List<EstudianteDTO> estudiantes = queryEstudiante.getResultList();
            return estudiantes;
        }

        public static Estudiante obtenerestudiantePorId (EntityManager em,int id){
            String jpql = "SeLECT e FROM Estudiante e WHERE e.dni = :id";
            Query query = em.createQuery(jpql, Estudiante.class);
            query.setParameter("id", id);
            Estudiante est = (Estudiante) query.getSingleResult();
            return est;

        }
    }
