package Factory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MySQLEntityManagerFactory extends Factory.EntityManagerFactory {

    private static final String PERSISTENCE_UNIT_NAME = "integrador2"; //deberia ser todo mayúscula

    private static EntityManagerFactory instance;

    public static EntityManagerFactory getEntityManagerFactory(){
        if(instance == null){
            instance =  Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return instance;
    }

    public static void shutdown(){
        if(instance !=null){
            instance.close();

        }
    }

}