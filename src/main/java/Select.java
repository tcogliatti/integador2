import EntityManagerFactory.JPAUtil;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Select {
    protected static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public static void main(String[] args) {
        // OPEN
        em.getTransaction().begin();
        //obtenerTodosLosEstudiantes();

//        @SuppressWarnings("unchecked")
        // obtenerEstudiantesPorGenero();

        //carrerasConInscriptos();
        // obtenerCarreraPorNombre("TUDAI");
        // estudiantesPorCarreraAgrupadosPorCiudad( "Veterinaria","Mafra");


        // CLOSE
        em.close();
        JPAUtil.shutdown();
    }
    //////////////////////////////////////////////////////////////
    /*
        (c)
        recuperar todos los estudiantes,
        y especificar algún criterio de ordenamiento simple.

    public static void obtenerTodosLosEstudiantes() {
        Query queryEstudiante = em.createNamedQuery(Estudiante.OBTENER_TODOS);
        List<Estudiante> estudiantes = queryEstudiante.getResultList();
        System.out.println("\nLista de los estudiantes ordenados ascendentemente por LU:");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }
     */

    //////////////////////////////////////////////////////////////
    /*
        (d)
        recuperar un estudiante, en base a su número de libreta universitaria.

    public static Estudiante buscarEstudiantePorLibretaUniversitaria(int lu) {
        Query busquedaPorLU = em.createQuery("SELECT e FROM Estudiante e WHERE e.lu = :lu");
        busquedaPorLU.setParameter("lu", lu);
        List<Estudiante> estudiantes = busquedaPorLU.getResultList();
        if (estudiantes.size() == 0){
            System.out.println("No se encuentran estudiantes con el numero de LU " + lu);
            return null;
        }else{
            System.out.println(estudiantes.get(0));
            return estudiantes.get(0);
        }
    }
     */

    //////////////////////////////////////////////////////////////
    /*
        (e)
        recuperar todos los estudiantes, en base a su género.
     */
    public static void obtenerEstudiantesPorGeneroSeleccionado() {
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
    /* (e)
        recuperar todos los estudiantes, en base a su género. OTRA VERSIÓN


    public static void obtenerEstudiantesPorGenero(String generoBuscado) {
        Query queryEstudiante = em.createQuery("SELECT e FROM Estudiante e  ORDER BY e.genero = :genero ASC");
        queryEstudiante.setParameter("genero", generoBuscado);
        List<Estudiante> estudiantes = queryEstudiante.getResultList();
        //System.out.println("\nLista de los estudiantes con el genero " + genero + ":");
        for (Estudiante est : estudiantes) {
            System.out.println(est.getGenero() +" - "+ est.getApellido()+" "+est.getNombre()+", "+"DNI: "+est.getDni()+" " +est.getDireccion());
        }
    }
     */
    //////////////////////////////////////////////////////////////
    /*
        (f)
        recuperar las carreras con estudiantes inscriptos,
        y ordenar por cantidad de inscriptos.

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
     */


    //////////////////////////////////////////////////////////////
    /*
        (g)
        recuperar los estudiantes de una determinada carrera,
        filtrado por ciudad de residencia.


    public static void estudiantesPorCarreraAgrupadosPorCiudad( String carrera, String ciudad) {
        Query query = em.createQuery(
                " SELECT m  FROM Matricula m WHERE  m.estudiante.direccion LIKE :ciudad AND m.carrera.nombre LIKE :carrera") ;
        query.setParameter("carrera", carrera);
        query.setParameter("ciudad", ciudad);
        List<Matricula> matriculas = query.getResultList();
        System.out.println("\nLista de estudiantes de la carrera "+carrera+" agrupados por ciudad seleccionada: "+ciudad);
        if (matriculas.size() == 0)
            System.out.println("No se encuentran estudiantes inscriptos en la carrera " + carrera+ " de la ciudad seleccionada.");
        else{
            for (Matricula matr : matriculas) {
                System.out.println(matr.getCarrera().getNombre()+" Estudiante: "+ matr.getEstudiante().getApellido()+" "+ matr.getEstudiante().getNombre()+", ciudad: "+ matr.getEstudiante().getDireccion());
            }
        }


    } */

        ////////////////////////////////////////////////////////////// AUXILIARES

    public static Carrera obtenerCarreraPorNombre(String nombreCarrera){
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

    public static void carrerasConEstudiantesInscriptos() {
        Query queryCarreras = em.createQuery(
                "SELECT c.nombre, c.duracion, " +
                        "COUNT(m.estudiante_dni) as cantEstudiantesInscriptos " +
                        "FROM carrera c " +
                        "INNER JOIN matricula m ON c.idCarrera= m.carrera_idCarrera " +
                        "WHERE M.estudiante_dni>0 " +
                        "GROUP BY c.idCarrera " +
                        "ORDER BY cantEstudiantesInscriptos DESC"
                        );

        List<Carrera> carreras = queryCarreras.getResultList();
        System.out.println("\nLista de carreras con estudiantes ordenados por matricula:");
        for (Carrera carrera : carreras) {
            System.out.println(carrera );
        }
    }

    public static List<Carrera> carrerasConEstudiantesInscriptosMatr() {
        Query queryCarreras = em.createQuery(
                "SELECT  c FROM Carrera c JOIN FETCH c.matriculados m ORDER BY c.nombre ASC, m.inscripcion ASC, m.graduacion DESC"

        );
        List<Carrera> carreras = queryCarreras.getResultList();
        return carreras;
    }

    public static List listasEstudiantesInscriptos(int id_carrera){

        String jpql = "SELECT c.nombre, c.duracion, " +
                "COUNT(m.estudiante_dni) as cantEstudiantesInscriptos " +
                "FROM carrera c INNER JOIN matricula m ON " +
                "c.idCarrera= m.carrera_idCarrera WHERE M.estudiante_dni>0 " +
                "GROUP BY c.idCarrera ORDER BY cantEstudiantesInscriptos DESC;";
        Query query = em.createQuery(jpql);

        return query.getResultList();

    }


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
