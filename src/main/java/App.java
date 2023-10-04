import EntityManagerFactory.JPAUtil;
import dao.CarreraDAO;
import dao.EstudianteDAO;
import dao.MatriculaDAO;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;
import jakarta.persistence.EntityManager;

import java.util.List;

public class App {

    protected static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();


    public static void main(String[] args) {

        // a) Dar de alta un ESTUDIANTE

        EstudianteDAO estudianteDAO = new EstudianteDAO();
        CarreraDAO carreraDAO = new CarreraDAO();
        MatriculaDAO matriculaDAO= new MatriculaDAO();
        CSVUtil carga = new CSVUtil();

        carga.cargarTablasBaseDatos();

        /**
            try{
                em.getTransaction().begin();
                estudianteDAO.cargarEstudiante(em, 32610, 14464432, "Mauricio", "Galcerán", 57, "Male", "Cacharí");
                em.getTransaction().commit();
            }catch (Exception e){
                em.getTransaction().rollback();
                e.printStackTrace();
                em.close();
            }
        */

        /*
            (c) recuperar todos los estudiantes,
            y especificar algún criterio de ordenamiento simple.
        */

            em.getTransaction().begin();
            List<Estudiante> estudiantes = estudianteDAO.obtenerTodosLosEstudiantes(em);

            System.out.println("\nLista de los estudiantes ordenados ascendentemente por LU:");
            for (Estudiante estudiante : estudiantes) {
                System.out.println(estudiante);
            }


        /*  (d)
            recuperar un estudiante, en base a su número de libreta universitaria.
         */

        Estudiante estudianteBuscadoPorLU = estudianteDAO.buscarEstudiantePorLibretaUniversitaria(em, 32000);
        if (estudianteBuscadoPorLU == null){
            System.out.println("No se encuentran estudiantes con el numero de LU ");
        }else{
            System.out.println("\nBusqueda estudiante por LU:  "+estudianteBuscadoPorLU.getLu() +"\n" +estudianteBuscadoPorLU + "\n");
        }

        /* (e)
            recuperar todos los estudiantes, en base a su género.
        */
        String genero = "Female";
        List<Estudiante> estudiantesPorGenero = estudianteDAO.obtenerEstudiantesPorGenero(em, genero);
        System.out.println("\nListado estudiantes con el género: "+genero );
        for (Estudiante est : estudiantesPorGenero) {
            System.out.println( est.getApellido()+" "+est.getNombre()+", "+"DNI: "+est.getDni()+" " +est.getDireccion());
        }

        /* f)
            recuperar las carreras con estudiantes inscriptos,
            y ordenar por cantidad de inscriptos.
        */
        /**
        carreraDAO.altaCarrera(em, 16, "Prueba", 4);
        em.getTransaction().commit();
         */
        List<Carrera> carreras = carreraDAO.carrerasConInscriptos(em);
        System.out.println("\nLista de carreras con estudiantes ordenados por matricula:");
        for (Carrera carrera : carreras) {
            System.out.println(carrera.getNombre()+" - "+carrera.getMatriculados().size());
        }

        /*
            (g)
            recuperar los estudiantes de una determinada carrera,
            filtrado por ciudad de residencia.
        */
        String nombreCarrera = "TUDAI";
        String ciudad = "Rauch";
        List<Matricula> matriculas = matriculaDAO.estudiantesPorCarreraAgrupadosPorCiudad(em, nombreCarrera, ciudad );
        System.out.println("\nLista de estudiantes de la carrera "+nombreCarrera+" agrupados por ciudad seleccionada: "+ciudad);
        if (matriculas.size() == 0)
            System.out.println("No se encuentran estudiantes inscriptos en la carrera " + nombreCarrera+ " de la ciudad seleccionada.");
        else {
            for (Matricula matr : matriculas) {
                System.out.println(matr.getCarrera().getNombre() + " Estudiante: " + matr.getEstudiante().getApellido() + " " + matr.getEstudiante().getNombre() + ", ciudad: " + matr.getEstudiante().getDireccion());
            }
        }
        em.close();
        JPAUtil.shutdown();
    }
}


/**
 * Select.obtenerCarreraPorNombre("TUDAI");
 *
 *         Select.carrerasConInscriptos();
 *
 *         List<Carrera> datosCarreras = Select.carrerasConEstudiantesInscriptosMatr();
 *         for (Carrera c: datosCarreras
 *         ) {
 *             System.out.println(c.getNombre());
 *             for (Iterator<Matricula> it = c.getMatriculados().iterator(); it.hasNext(); ) {
 *                 Matricula cMatr = it.next();
 *                 System.out.println(cMatr.getEstudiante()+" -  Inscripción:   "+ cMatr.getInscripcion()+" - Graduación: "+cMatr.getGraduacion());
 *             }
 *             System.out.println();
 *
 *         }
 *         System.out.println();
 */