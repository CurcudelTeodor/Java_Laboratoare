package repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory instance;

    // Private constructor to prevent instantiation
    private EntityManagerFactorySingleton() {
    }

    public static EntityManagerFactory getInstance() {
        if (instance == null) {
            instance = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return instance;
    }

    public static void close() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }
}
