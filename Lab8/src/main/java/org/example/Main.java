package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AlbumDAO albumDAO = new AlbumDAO();
            int[] genreIds = {1, 2, 3}; // example of genre ids

            //Cream un nou album
            Album album = new Album();
            album.setReleaseYear(2022);
            album.setTitle("My Album");
            album.setArtist("My Artist");
            album.setGenreIds(genreIds);
            albumDAO.createAlbum(album.getReleaseYear(),album.getTitle(), album.getId(), album.getGenreIds());

            //luam toate albumele
            List<Album> albums = albumDAO.getAll();
            for (Album a : albums) {
                System.out.println(a);
            }

            //update
            Album albumToUpdate = albums.get(0);
            albumToUpdate.setTitle("Titlu 1234");
            albumDAO.update(albumToUpdate);

            //delete
            Album albumToDelete = albums.get(1);
            albumDAO.delete(albumToDelete.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}