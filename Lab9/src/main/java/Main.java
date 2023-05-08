import com.github.javafaker.Faker;
import entity.AlbumEntity;
import entity.ArtistEntity;
import entity.GenresEntity;
import jakarta.persistence.*;
import repository.AlbumRepository;
import repository.ArtistRepository;
import repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();

//        try {
//            transaction.begin();


        ArtistRepository artistRepository = new ArtistRepository();
        GenreRepository genreRepository = new GenreRepository();
        AlbumRepository albumRepository = new AlbumRepository();

//        ArtistEntity sia = new ArtistEntity();
//        sia.setName("Sarpe");
//        artistRepository.create(sia);
//
//        GenresEntity gen1 = new GenresEntity();
//        gen1.setName("gen1");
//        genreRepository.create(gen1);
//
//        AlbumEntity album1 = new AlbumEntity();
//        album1.setTitle("Album shuka");
//        album1.setArtistId(117);
//        album1.setReleaseYear(2023);
//        album1.getGenres().add(gen1);
//        albumRepository.create(album1);

        List<ArtistEntity> artists = artistRepository.findByName("Travis Scott");
        System.out.println("Artists with name containing 'aa':");
        for(ArtistEntity arti : artists)
            System.out.println(arti.getName());

        GenresEntity genreForId = genreRepository.findByID(118);
        System.out.println(genreForId.getName());


        Faker faker = new Faker();
        List<ArtistEntity> artistsFake = new ArrayList<>();
        List<AlbumEntity> albumsFake = new ArrayList<>();


        long start = System.currentTimeMillis();
        artistRepository.deleteAll();


        for (int i = 0; i < 1000; i++) {
            ArtistEntity artist = new ArtistEntity();
            artist.setName(faker.name().fullName());
            artistRepository.create(artist);
            artistsFake.add(artist);

            AlbumEntity album = new AlbumEntity();
            album.setTitle(faker.book().title());
            album.setReleaseYear(faker.number().numberBetween(1900, 2100));
            albumsFake.add(album);
        }

        for (AlbumEntity album : albumsFake) {
            albumRepository.create(album);
        }
        long end = System.currentTimeMillis();
        System.out.println("Timp de executie: " + (end - start) + " ms");




//            transaction.commit();
//        } finally {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            entityManager.close();
//            entityManagerFactory.close();
//        }
    }
}
