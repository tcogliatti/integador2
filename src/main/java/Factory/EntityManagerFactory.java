package Factory;

public abstract class EntityManagerFactory {
    public static final int MYSQL=1;
    public static EntityManagerFactory getEntityManager(int db_factory){
        switch (db_factory){
            case MYSQL:
                MySQLEntityManagerFactory.getEntityManagerFactory();
            default:
                return null;
        }
    }
}
