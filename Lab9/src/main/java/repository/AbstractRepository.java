package repository;

import jakarta.persistence.*;
import java.util.List;

public abstract class AbstractRepository<T> {

    private final Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<T> findByName(String namePattern) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
        TypedQuery<T> query = entityManager.createNamedQuery(entityClass.getSimpleName() + ".findByName", entityClass);
        //cautam Entitati cu un anumit pattern, nu neaparat cu un nume 100% corect
        query.setParameter("nume", "%" + namePattern + "%");
        List<T> entities = query.getResultList();
        entityManager.close();
        return entities;
    }

    // Id-ul e unic -> Nu mai returnam o lista
    public T findByID(int idToBeLookedUp) {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
        TypedQuery<T> query = entityManager.createNamedQuery(entityClass.getSimpleName() + ".findByID", entityClass);

        query.setParameter("numar", idToBeLookedUp);
        T entity = query.getSingleResult();
        entityManager.close();
        return entity;
    }

//    public List<T> findById(int id) {
//        EntityManager entityManager = repository.EntityManagerFactorySingleton.getInstance().createEntityManager();
//
//        Album album = em.find(Album.class, id);
//        em.close();
//        if(album == null) {
//            throw new EntityNotFoundException("Nu am gasit albumul cu: " + id);
//        }
//        return album;
//    }

    public void deleteAll() {
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM " + entityClass.getSimpleName()).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

