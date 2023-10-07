package Factory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil extends Factory.EntityManagerFactory {

    private static final String PERSISTENCE_UNIT_NAME = "integrador2"; //deberia ser todo mayúscula
    private static EntityManagerFactory em;

    public static EntityManagerFactory getEntityManagerFactory(){
        if(em == null){
            em =  Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return em;
    }

    public static void shutdown(){
        if(em !=null){
            em.close();
        }
    }

}