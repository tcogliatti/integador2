import dao.Carrera;
import dao.Estudiante;
import dao.Matricula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Insert {
    protected final static String PERSISTENCE = "integrador2";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE);
    protected static EntityManager em = emf.createEntityManager();
    public static final String PATH = "./src/main/resources/csv/";
    public static final String ESTUDIANTES_CSV = "estudiantes.csv";
    public static final String CARRERAS_CSV = "carreras.csv";
    public static final String MATRICULA_CSV = "estudianteCarrera.csv";

    public static void main(String[] args) {

        // CARGA DE ESTUDIANTES
        // CARGA DE CARRERAS
        try {
            em.getTransaction().begin();
            cargaDeEstudiantes();
            cargaDeCarreras();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            em.close();
            emf.close();
        }

        // CARGA DE MATRICULA
        try {
            em.getTransaction().begin();
            cargaDeMatriculas();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
    //////////////////////////////////////// CARGA DE ESTUDIANTES
    public static void cargaDeEstudiantes() {
        Estudiante estudiante;
        CSVParser parser = null;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(PATH + ESTUDIANTES_CSV));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Cargando Estudiantes --> ");
        for (CSVRecord row : parser) {
            int nro_libreta = Integer.parseInt(row.get("LU"));
            int dni = Integer.parseInt(row.get("DNI"));
            int edad = Integer.parseInt(row.get("edad"));
            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            String genero = row.get("genero");
            String direccion = row.get("ciudad");
            estudiante = new Estudiante(nro_libreta, dni, nombre, apellido, edad, genero, direccion);
            em.persist(estudiante);
            System.out.println("\t" + estudiante);
        }
        System.out.println("\n                  --> proceso terminado /_");
    }
    //////////////////////////////////////// CARGA DE CARRERAS
    public static void cargaDeCarreras() {
        Carrera carrera;
        CSVParser parser = null;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(PATH + CARRERAS_CSV));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Cargando Carreras --> ");
        for (CSVRecord row : parser) {
            int idCarrera = Integer.parseInt(row.get("id_carrera"));
            int duracion = Integer.parseInt(row.get("duracion"));
            String nombre = row.get("carrera");
            carrera = new Carrera(idCarrera, nombre, duracion);
            em.persist(carrera);
            System.out.println("\t" + carrera);
        }
        System.out.println("\n                  --> proceso terminado /_");
    }
    //////////////////////////////////////// CARGA DE MATRICULA
    public static void cargaDeMatriculas() {
        Matricula matricula;
        Carrera carrera;
        Estudiante estudiante;
        CSVParser parser = null;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(PATH + MATRICULA_CSV));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Cargando Matriculados --> ");
        for (CSVRecord row : parser) {
            int id = Integer.parseInt(row.get("id"));
            int idCarrera = Integer.parseInt(row.get("id_carrera"));
            carrera = em.find(Carrera.class, idCarrera);
            int idEstudiante = Integer.parseInt(row.get("id_estudiante"));
            estudiante = em.find(Estudiante.class, idEstudiante);
            int duracion = Integer.parseInt(row.get("antiguedad"));
            int inscripcion = Integer.parseInt(row.get("inscripcion"));
            int graduacion = Integer.parseInt(row.get("graduacion"));
            matricula = new Matricula(id, carrera, estudiante, inscripcion, graduacion, duracion);
            em.persist(matricula);
            System.out.println("\t" + matricula);
        }
        System.out.println("\n                  --> proceso terminado /_");
    }
}
