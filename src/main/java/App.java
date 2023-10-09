import Factory.MySQLEntityManagerFactory;
import Repository.CarreraRepository;
import Repository.EstudianteRepository;
import Repository.MatriculaRepository;
import Repository.ReporteFinalRepository;
import dto.CarreraCiudadDTO;
import dto.CarreraDTO;
import dto.EstudianteDTO;
import dto.ReporteFinalDTO;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;
import jakarta.persistence.EntityManager;

import java.util.*;

public class App {

    protected static EntityManager em = MySQLEntityManagerFactory.getEntityManagerFactory().createEntityManager();

    public static void main(String[] args) {


        EstudianteRepository estudianteRepository = new EstudianteRepository();
        CarreraRepository carreraRepository = new CarreraRepository();
        MatriculaRepository matriculaRepository = new MatriculaRepository();
        ReporteFinalRepository reporteFinalRepository = new ReporteFinalRepository();
        CSVUtil carga = new CSVUtil();


        /*
            INIT
            Carga de tablas desde archivos CSV.
        */

        carga.cargarTablasBaseDatos();

        em.getTransaction().begin();

        /*
            (a) dar de alta un estudiante.
        */

        System.out.println("\na) Dar de alta un ESTUDIANTE \n");
        estudianteRepository.cargarEstudiante(em, 20000, 29000009, "Javier", "Vasquez", 72, "Male", "Rauch");

        /*
            (b) matricular un estudiante en una carrera.
        */

        System.out.println("\nb) Matricular un estudiante en un carrera \n");
        Carrera c = em.find(Carrera.class, "1");
        Estudiante e = em.find(Estudiante.class, "29000009");
        Matricula matricula = new Matricula(115, c, e, 2023, 0, 0);
        matriculaRepository.matricularEstudianteEnCarrera(em, matricula);
        System.out.println("El estudiante fue matriculado con éxito");

        /*
            (c)
            recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        */

        System.out.println("\nc) Recuperar todos los estudiantes, y especificar algún criterio " +
                "de ordenamiento simple \n");

        List<EstudianteDTO> estudiantes = estudianteRepository.obtenerTodosLosEstudiantes(em);
        System.out.println("Lista de los estudiantes ordenados ascendentemente por DNI:\n");
        for (EstudianteDTO estudiante : estudiantes) {
            System.out.println(estudiante);
        }

        /*
            (d)
            recuperar un estudiante, en base a su número de libreta universitaria.
        */

        System.out.println("\n(d) Recuperar un estudiante, en base a su número de libreta universitaria \n");
        EstudianteDTO estudianteBuscadoPorLU = estudianteRepository.buscarEstudiantePorLibretaUniversitaria(em, 20000);
        if (estudianteBuscadoPorLU == null) {
            System.out.println("No se encuentran estudiantes con el numero de LU ");
        } else {
            System.out.println("Busqueda estudiante por LU:  " + estudianteBuscadoPorLU.getLu());
            System.out.println(estudianteBuscadoPorLU.getApellido() + ", " + estudianteBuscadoPorLU.getNombre() + ", " + "DNI: " + estudianteBuscadoPorLU.getDni() + " " + estudianteBuscadoPorLU.getDireccion());
        }

        /* (e)
            recuperar todos los estudiantes, en base a su género.
        */

        System.out.println("\ne) Recuperar todos los estudiantes, en base a su género");
        String genero = "Female";
        List<EstudianteDTO> estudiantesPorGenero = estudianteRepository.obtenerEstudiantesPorGenero(em, genero);
        System.out.println("\nListado estudiantes con el género: " + genero);
        for (EstudianteDTO est : estudiantesPorGenero) {
            System.out.println("Género: " + est.getGenero() + " Estudiante: " + est.getApellido() + ", " + est.getNombre() + ", " + "DNI: " + est.getDni() + " Ciudad: " + est.getDireccion());
        }

        /*
            (f)
            recuperar las carreras con estudiantes inscriptos,
            y ordenar por cantidad de inscriptos.
        */

        System.out.println("\nf) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.");

        List<CarreraDTO> carreras = carreraRepository.carrerasConInscriptos(em);
        System.out.println("\nLista de carreras con estudiantes ordenados por matricula:\n");
        for (CarreraDTO carrera : carreras) {
            System.out.println(carrera.getNombre() + " - " + carrera.getCantInscriptos());
        }

        /*
            (g)
            recuperar los estudiantes de una determinada carrera,
            filtrado por ciudad de residencia.
        */

        System.out.println("\ng) Recuperar los estudiantes de una determinada carrera,filtrado por ciudad de residencia.\n");
        String nombreCarrera = "TUDAI";
        String ciudad = "Rauch";

        List<CarreraCiudadDTO> carreraXCiudad = matriculaRepository.estudiantesPorCarreraAgrupadosPorCiudad(em, nombreCarrera, ciudad);
        System.out.println("Lista de estudiantes de la carrera " + nombreCarrera + " agrupados por ciudad seleccionada: " + ciudad + "\n");
        if (carreraXCiudad.size() == 0)
            System.out.println("No se encuentran estudiantes inscriptos en la carrera " + nombreCarrera + " de la ciudad seleccionada.");
        else {
            for (CarreraCiudadDTO carrera : carreraXCiudad) {
                System.out.println(carrera.getNombre_carrera() + " Estudiante: " + carrera.getApellido_estudiante() + " " + carrera.getNombre_estudiante() + "   LU: " + carrera.getLU() + ", ciudad: " + carrera.getCiudad());
            }
        }

        /*
            3.
            Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año.
            Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.
        */

        System.out.println("\n Reporte Final \n");
        List<ReporteFinalDTO> reporte = reporteFinalRepository.reporteFinal(em);
        for (ReporteFinalDTO r : reporte) {
            System.out.println(r);
        }
        em.close();
        MySQLEntityManagerFactory.shutdown();
    }
}

