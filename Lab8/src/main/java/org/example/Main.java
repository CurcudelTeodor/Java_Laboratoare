package org.example;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        // -------------- Artisti ----------------

        /*
        1 - Travis Scott
        2 - Kanye West
        3 - Lil Baby
        4 - Lil Wayne
        5 - Sam Cooke
        6 - Radiohead
        7 - Freddie Dredd
        */


        /*
        ArtistDAO artistDAO = new ArtistDAO();
        Artist travis = new Artist("Travis Scott");
        Artist kanye = new Artist("Kanye West");
        Artist baby = new Artist("Lil Baby");
        Artist wayne = new Artist("Lil Wayne");
        Artist sam = new Artist("Sam Cooke");
        Artist head = new Artist("Radiohead");
        Artist freddie = new Artist("Freddie Dredd");

        List <Artist> listaArtisti = new LinkedList<>();
        listaArtisti.add(travis);
        listaArtisti.add(kanye);
        listaArtisti.add(baby);
        listaArtisti.add(wayne);
        listaArtisti.add(sam);
        listaArtisti.add(head);
        listaArtisti.add(freddie);

        artistDAO.createArtist(travis.getName());
        artistDAO.createArtist(kanye.getName());
        artistDAO.createArtist(baby.getName());
        artistDAO.createArtist(wayne.getName());
        artistDAO.createArtist(sam.getName());
        artistDAO.createArtist(head.getName());
        artistDAO.createArtist(freddie.getName());


        System.out.println(artistDAO.findByName("Travis Scott",listaArtisti));
        System.out.println(travis);

        System.out.println("----------");
        System.out.println(artistDAO.findById(3, listaArtisti));
        System.out.println(baby);

        System.out.println("----------");
        List<Artist> listaDinBD = artistDAO.getAll();
        for (Artist artist : listaDinBD){
            System.out.println(artist.getName());
        }
        */

        // -------------- Artisti ----------------


        // -------------- Genuri ----------------

        /*
        1 - Rap
        2 - Pop
        3 - Phonk
        4 - Soul
        5 - Rock
        6 - Electronic
        7 - Latin
        */

        /*
        GenreDAO genreDAO = new GenreDAO();
        Genre rap = new Genre("Rap");
        Genre pop = new Genre("Pop");
        Genre phonk = new Genre("Phonk");
        Genre soul = new Genre("Soul");
        Genre rock = new Genre ("Rock");
        Genre electronic = new Genre("Electronic");
        Genre latin = new Genre("Latin");

        List <Genre> listaGenuri = new LinkedList<>();
        listaGenuri.add(rap);
        listaGenuri.add(pop);
        listaGenuri.add(phonk);
        listaGenuri.add(soul);
        listaGenuri.add(rock);
        listaGenuri.add(electronic);
        listaGenuri.add(latin);

        genreDAO.createGenre(rap.getName());
        genreDAO.createGenre(pop.getName());
        genreDAO.createGenre(phonk.getName());
        genreDAO.createGenre(soul.getName());
        genreDAO.createGenre(rock.getName());
        genreDAO.createGenre(electronic.getName());
        genreDAO.createGenre(latin.getName());

        System.out.println(genreDAO.findById(1,listaGenuri));
        System.out.println(rap);
        System.out.println("-----------");

        System.out.println(genreDAO.findByName("Phonk",listaGenuri));
        System.out.println(phonk);
        System.out.println("-------------");

        List<Genre> listaGenuriDinBD = genreDAO.getAll();
        for (Genre gen : listaGenuriDinBD) {
            System.out.println(gen.getName());
        }

        */
        // -------------- Genuri ----------------

        // -------------- Albume ----------------

        /*
        AlbumDAO albumDAO = new AlbumDAO();

        int[] astroGenre = {1,4}; //rap + soul
        Album astro = new Album (2018, "Astroworld",1, astroGenre);

        int[] yeezusGenre = {1,6}; //rap + electronic
        Album yeezus = new Album (2013, "Yeezus", 2, yeezusGenre);

        int[] myTurnGenre = {1}; //rap
        Album myTurn = new Album(2020, "My Turn", 3, myTurnGenre);

        int[] carterGenre = {1,4}; //rap + soul
        Album carter = new Album(2008, "Tha Carter III", 4,carterGenre);

        int[] portraitGenre = {4,7}; //soul + latin
        Album portrait = new Album(2003, "Portrait of a Legend",5,portraitGenre);

        int[] amnesiacGenre = {5,6}; //rock + electronic
        Album amnesiac = new Album(2001, "Amnesiac",6,amnesiacGenre);

        int[] dreddGenre = {1,3}; //phonk
        Album dreddalicious = new Album(2019, "Dreddalicious",7,dreddGenre);

        albumDAO.createAlbum(astro);
        albumDAO.createAlbum(yeezus);
        albumDAO.createAlbum(myTurn);
        albumDAO.createAlbum(carter);
        albumDAO.createAlbum(portrait);
        albumDAO.createAlbum(amnesiac);
        albumDAO.createAlbum(dreddalicious);


        List<Album> listaDinBD = albumDAO.getAll();
        for (Album album : listaDinBD){
            System.out.println(album.getTitle());
        }

         */
        // -------------- Albume ----------------
        
    }
}