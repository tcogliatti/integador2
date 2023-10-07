package Factory;

public class EntityManagerFactory {
    public static final int MYSQL=1;
    public static EntityManagerFactory getEntityManager(int db_factory){
        switch (db_factory){
            case MYSQL:
                JPAUtil.getEntityManagerFactory();
            default:
                return null;
        }
    }
}
