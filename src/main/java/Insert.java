import dao.Estudiante;
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

    public static void main(String[] args) {

        try {
            em.getTransaction().begin();
            cargaDeEstudiantes();
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
            emf.close();
        }

    }

    public static void cargaDeEstudiantes(){
        Estudiante estudiante;
        CSVParser parser = null;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(PATH + ESTUDIANTES_CSV));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cargando Estudiantes --> ");
        for (CSVRecord row : parser) {
            int nro_libreta = Integer.parseInt(row.get("nro_libreta"));
            int dni = Integer.parseInt(row.get("dni"));
            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date nacimiento;
            try {
                nacimiento = sdf.parse(row.get("nacimiento"));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            char genero =  row.get("genero").charAt(0);
            String direccion = row.get("direccion");
            estudiante = new Estudiante(nro_libreta, dni, nombre, apellido, nacimiento, genero, direccion);
            em.persist(estudiante);
        }
    }
}
