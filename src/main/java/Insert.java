import EntityManagerFactory.JPAUtil;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;
import jakarta.persistence.EntityManager;

public class Insert {
    protected static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    ///////////// QUITAR ////////////////////

    public static void main(String[] args) {


         try{
         em.getTransaction().begin();
         cargarEstudiante(32000, 14461132, "Mauricio", "Galcerán", 57, "Femenino", "Cacharí");
         em.getTransaction().commit();
         }catch (Exception e){
         em.getTransaction().rollback();
         e.printStackTrace();
         em.close();
         JPAUtil.shutdown();
         }

/**
        // MATRICULAR ESTUDIANTE EN UNA CARRERA
        try {
            em.getTransaction().begin();
            Estudiante estudiante = Select.buscarEstudiantePorLibretaUniversitaria(32000);
            Carrera carrera = Select.obtenerCarreraPorNombre("TUDAI");
            matricularEstudianteEnCarrera(110, carrera, estudiante, 2023, 2026, 0);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            em.close();
            EntityManagerFactory.JPAUtil.shutdown();
        }
*/

    }
    //////////////////////////////////////// a) Dar de  ALTA un  ESTUDIANTE
    public static void cargarEstudiante(int nro_libreta, int dni, String nombre, String apellido, int edad, String genero, String direccion){
        Estudiante est;
        est = new Estudiante(nro_libreta, dni, nombre, apellido, edad, genero, direccion);
        em.persist(est);
    }

    //////////////////////////////////////// b) MATRICULAR un ESTUDIANTE en una  CARRERA
    public static void matricularEstudianteEnCarrera(int id, Carrera carrera, Estudiante estudiante, int inscripcion, int graduado, int antiguedad){
        Matricula matricular;
        matricular = new Matricula( id , carrera, estudiante, inscripcion, graduado, antiguedad);
        em.persist(matricular);
   }


}
