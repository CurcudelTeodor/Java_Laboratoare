package org.example;




import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;



//scriem si Singleton pt ca EntityManagerFactory e definit in
public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
