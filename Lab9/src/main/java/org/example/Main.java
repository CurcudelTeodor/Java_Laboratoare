package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

        Artist artist1 = new Artist("artst1");
        Artist quavo = new Artist("quavo");

        Genre rock = new Genre("rap");
        Genre pop = new Genre("pop");

        Album nightAtTheOpera = new Album(1975, "Album shukar", artist1);
        Album abbeyRoad = new Album(1969, "Culture 4", quavo);

        AlbumRepository albumRepository = new AlbumRepository();
        albumRepository.create(nightAtTheOpera);
        albumRepository.create(abbeyRoad);

        Album foundAlbum = albumRepository.findById(78);
        List<Album> foundAlbums = albumRepository.findByName("Culture 4");

        System.out.println(foundAlbum);
        System.out.println(foundAlbums);

        emf.close();
    }
}