import EntityManagerFactory.JPAUtil;
import Repository.CarreraRepository;
import Repository.EstudianteRepository;
import Repository.MatriculaRepository;
import dto.CarreraCiudadDTO;
import dto.CarreraDTO;
import dto.EstudianteDTO;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;
import jakarta.persistence.EntityManager;

import java.util.List;

public class App {

    protected static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();


    public static void main(String[] args) {



        EstudianteRepository estudianteRepository = new EstudianteRepository();
        CarreraRepository carreraRepository = new CarreraRepository();
        MatriculaRepository matriculaRepository = new MatriculaRepository();
        CSVUtil carga = new CSVUtil();

        carga.cargarTablasBaseDatos();

        System.out.println("\na) Dar de alta un ESTUDIANTE \n");
            try{
                em.getTransaction().begin();
                estudianteRepository.cargarEstudiante(em, 32610, 14464432, "Mauricio", "Galcerán", 57, "Male", "Cacharí");
                em.getTransaction().commit();
                System.out.println("El estudiante fue cargado con éxito");
            }catch (Exception e){
                em.getTransaction().rollback();
                e.printStackTrace();
                em.close();
            }

        System.out.println("\nb) Matricular un estudiante en un carrera \n");
        // MATRICULAR ESTUDIANTE EN UNA CARRERA
        try {
            em.getTransaction().begin();
            Carrera c= em.find(Carrera.class, "2");
            Estudiante e= em.find(Estudiante.class, "14464432");
            Matricula matricula = new Matricula(110, c, e, 2023, 2026, 3);

            matriculaRepository.matricularEstudianteEnCarrera(em,matricula);
            em.getTransaction().commit();
            System.out.println("El estudiante fue matriculado con éxito");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            em.close();
        }
        /*
            (c) recuperar todos los estudiantes,
            y especificar algún criterio de ordenamiento simple.
        */
        System.out.println("\nc) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple \n");

            em.getTransaction().begin();
            List<EstudianteDTO> estudiantes = estudianteRepository.obtenerTodosLosEstudiantes(em);

            System.out.println("Lista de los estudiantes ordenados ascendentemente por DNI:\n");
            for (EstudianteDTO estudiante : estudiantes) {
                System.out.println(estudiante);
            }


        /*  (d) Recuperar un estudiante, en base a su número de libreta universitaria.
         */
        System.out.println("\n(d) Recuperar un estudiante, en base a su número de libreta universitaria \n");
        EstudianteDTO estudianteBuscadoPorLU = estudianteRepository.buscarEstudiantePorLibretaUniversitaria(em,10383);
        if (estudianteBuscadoPorLU == null){
            System.out.println("No se encuentran estudiantes con el numero de LU ");
        }else {
            System.out.println("Busqueda estudiante por LU:  " + estudianteBuscadoPorLU.getLu() );
            System.out.println(estudianteBuscadoPorLU.getApellido()+", "+estudianteBuscadoPorLU.getNombre() + ", " + "DNI: " + estudianteBuscadoPorLU.getDni() + " " + estudianteBuscadoPorLU.getDireccion());
        }
        /* (e)
            recuperar todos los estudiantes, en base a su género.
        */
        System.out.println("\ne) Recuperar todos los estudiantes, en base a su género");

        String genero = "Female";
        List<EstudianteDTO> estudiantesPorGenero = estudianteRepository.obtenerEstudiantesPorGenero(em, genero);
        System.out.println("\nListado estudiantes con el género: "+genero );
        for (EstudianteDTO est : estudiantesPorGenero) {
            System.out.println( est.getApellido()+", "+est.getNombre()+", "+"DNI: "+est.getDni()+" " +est.getDireccion());
        }

        System.out.println("\nf) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.");

        List<CarreraDTO> carreras = carreraRepository.carrerasConInscriptos(em);
        System.out.println("\nLista de carreras con estudiantes ordenados por matricula:\n");
        for (CarreraDTO carrera : carreras) {
            System.out.println(carrera.getNombre()+" - "+carrera.getCantInscriptos());
        }

        /*
            (g)
            recuperar los estudiantes de una determinada carrera,
            filtrado por ciudad de residencia.
        */
        System.out.println("\ng) Recuperar los estudiantes de una determinada carrera,filtrado por ciudad de residencia.\n");
        String nombreCarrera = "TUDAI";
        String ciudad = "Rauch";
        List<CarreraCiudadDTO> carreraXCiudad = matriculaRepository.estudiantesPorCarreraAgrupadosPorCiudad(em, nombreCarrera, ciudad );
        System.out.println("Lista de estudiantes de la carrera "+nombreCarrera+" agrupados por ciudad seleccionada: "+ciudad+"\n");
        if (carreraXCiudad.size() == 0)
            System.out.println("No se encuentran estudiantes inscriptos en la carrera " + nombreCarrera+ " de la ciudad seleccionada.");
        else {
            for (CarreraCiudadDTO carrera : carreraXCiudad) {
                System.out.println(carrera.getNombre_carrera() + " Estudiante: " + carrera.getApellido_estudiante() + " " + carrera.getNombre_estudiante() + ", ciudad: " + carrera.getCiudad());
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