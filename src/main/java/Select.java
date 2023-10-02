import dao.Carrera;
import dao.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Select {
    protected final static String PERSISTENCE = "integrador2";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // OPEN
        em.getTransaction().begin();
//        @SuppressWarnings("unchecked")

//        obtenerEstudiantesPorGenero();

//        carrerasConInscriptos();




        // CLOSE
        em.close();
        emf.close();
    }
    //////////////////////////////////////////////////////////////
    /*
        (c)
        recuperar todos los estudiantes,
        y especificar algún criterio de ordenamiento simple.
     */
    public static void obtenerTodosLosEstudiantes() {
        Query queryEstudiante = em.createNamedQuery(Estudiante.OBTENER_TODOS);
        List<Estudiante> estudiantes = queryEstudiante.getResultList();
        System.out.println("\nLista de los estudiantes ordenados ascendentemente por DNI:");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }
    //////////////////////////////////////////////////////////////
    /*
        (d)
        recuperar un estudiante, en base a su número de libreta universitaria.
     */
    public static void buscarEstudiantePorLibretaUniversitaria(int lu) {
        Query busquedaPorLU = em.createQuery("SELECT e FROM Estudiante e WHERE e.lu = :lu");
        busquedaPorLU.setParameter("lu", lu);
        List<Estudiante> estudiantes = busquedaPorLU.getResultList();
        if (estudiantes.size() == 0)
            System.out.println("No se encuentran estudiantes con el numero de LU " + lu);
        else
            System.out.println(estudiantes.get(0));
    }
    //////////////////////////////////////////////////////////////
    /*
        (e)
        recuperar todos los estudiantes, en base a su género.
     */
    public static void obtenerEstudiantesPorGenero() {
        HashMap<Integer, String> generoMap = new HashMap<>();
        generoMap.put(1, "Female");
        generoMap.put(2, "Male");
        generoMap.put(3, "Genderfluid");
        generoMap.put(4, "Non-binary");
        generoMap.put(5, "Polygender");
        generoMap.put(6, "Agender");
        generoMap.put(7, "Bigender");
        String genero = obtenerOpcion(generoMap);
        Query queryEstudiante = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero");
        queryEstudiante.setParameter("genero", genero);
        List<Estudiante> estudiantes = queryEstudiante.getResultList();
        System.out.println("\nLista de los estudiantes con el genero " + genero + ":");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }

    //////////////////////////////////////////////////////////////
    /*
        (f)
        recuperar las carreras con estudiantes inscriptos,
        y ordenar por cantidad de inscriptos.
     */
    public static void carrerasConInscriptos() {
        Query queryCarreras = em.createQuery(
                "SELECT c FROM Carrera c " +
                        "WHERE c.idCarrera IN (SELECT m.carrera.idCarrera FROM Matricula m) " +
                        "ORDER BY SIZE(c.matriculados) ASC");
        List<Carrera> carreras = queryCarreras.getResultList();
        System.out.println("\nLista de carreras con estudiantes ordenados por matricula:");
        for (Carrera carrera : carreras) {
            System.out.println(carrera.getNombre()+" - "+carrera.getMatriculados().size());
        }
    }
    //////////////////////////////////////////////////////////////
    /*
        (g)
        recuperar los estudiantes de una determinada carrera,
        filtrado por ciudad de residencia.
     */

    ////////////////////////////////////////////////////////////// AUXILIARES
    public static String obtenerOpcion(HashMap<Integer, String> mapa) {
        int opt = 0;
        boolean opcionCorrecta = false;

        System.out.println("Lista un item de la lista introduciendo valores numericos del 1 al "+mapa.size());
        for (int i = 1; i < mapa.size(); i++) {
            System.out.println("\t" + i + ". " + mapa.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        while (!opcionCorrecta) {
            if (scanner.hasNextInt()) {
                opt = scanner.nextInt();
                if (opt > 7 || opt < 1)
                    System.out.println("El valor ingresado esta fuera de los rangos");
                else
                    opcionCorrecta = true;
            } else {
                System.out.println("El valor ingresado no es numérico");
                scanner = new Scanner(System.in);
            }
        }
        scanner.close();
        return mapa.get(opt);
    }

}
