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

        obtenerEstudiantesPorGeneroSeleccionado();

        //carrerasConInscriptos();
        // obtenerCarreraPorNombre("TUDAI");
        listasEstudiantesInscriptos(1);

         estudiantesPorCarreraAgrupadosPorCiudad( "Veterinaria","Mafra");
        carrerasConEstudiantesInscriptos();
        estudiantesPorCarreraAgrupadosPorCiudad(  "Abogacia",  "Mafra");

        // CLOSE
        em.close();
        JPAUtil.shutdown();
    }


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


    }


    public static void carrerasConEstudiantesInscriptos() {
        Query queryCarreras = em.createNativeQuery(
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



    public static void  listasEstudiantesInscriptos(int idCarrera) {
//No funciona
        String jpql = "SELECT c.nombre, c.duracion, " +
                "COUNT(m.estudiante_dni) as cantEstudiantesInscriptos " +
                "FROM carrera c INNER JOIN matricula m ON " +
                "c.idCarrera= m.carrera_idCarrera WHERE M.estudiante_dni>0 " +
                "GROUP BY c.idCarrera ORDER BY cantEstudiantesInscriptos DESC ";
        Query query = em.createNativeQuery(jpql);
        List<Object[]> resultados = query.getResultList();
        for (Object[] r : resultados) {
            String nombre = (String) r[0];
            int duracion = (Integer) r[1];
            Long cantInscriptos = (Long) r[2];
            System.out.println(nombre + " , duración : " + duracion + ", Cantidad inscriptos: " + cantInscriptos);

        }


    }}
