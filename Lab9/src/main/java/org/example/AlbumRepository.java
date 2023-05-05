package org.example;


import jakarta.persistence.*;

import java.util.List;


public class AlbumRepository {

    //PU -> persistance unit
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("AlbumsPU");

    public void create(Album album) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        em.getTransaction().begin();
        em.persist(album);
        em.getTransaction().commit();
        em.close();
    }

    public Album findById(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Album album = em.find(Album.class, id);
        em.close();
        if(album == null) {
            throw new EntityNotFoundException("Nu am gasit albumul cu: " + id);
        }
        return album;
    }

    public List<Album> findByName(String namePattern) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        TypedQuery<Album> query = em.createNamedQuery("Album.findByName", Album.class);
        query.setParameter("namePattern", "%" + namePattern + "%");
        List<Album> albums = query.getResultList();
        em.close();
        return albums;
    }
}

