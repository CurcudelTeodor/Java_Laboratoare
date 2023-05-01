package org.example;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportDataFromCSV {
    public static void importDataFromCSV() {
        String csvFilePath = "./albumlist.csv";
        BufferedReader br = null;
        String line = "";
        String csvDelimiter = ",";

        try (Connection connection = Database.getConectionFromPool()) {
            String insertArtistQuery = "INSERT INTO artists (name) VALUES (?) ON CONFLICT (name) DO NOTHING";
            String insertGenreQuery = "INSERT INTO genres (name) VALUES (?) ON CONFLICT (name) DO NOTHING";
            String insertAlbumQuery = "INSERT INTO albums (release_year, title, artist_id) VALUES (?, ?, ?)";
            String insertAlbumGenreQuery = "INSERT INTO album_genres (album_id, genre_id) VALUES (?, ?)";

            PreparedStatement insertArtistStatement = connection.prepareStatement(insertArtistQuery);
            PreparedStatement insertGenreStatement = connection.prepareStatement(insertGenreQuery);
            PreparedStatement insertAlbumStatement = connection.prepareStatement(insertAlbumQuery);
            PreparedStatement insertAlbumGenreStatement = connection.prepareStatement(insertAlbumGenreQuery);

            // Citim fisierul CSV
            br = new BufferedReader(new FileReader(csvFilePath));
            // Citim antetul (Year, Album, Artist, Genre)
            br.readLine();
            while ((line = br.readLine()) != null) {
                // Extragem datele din randul din CSV
                String[] data = line.split(csvDelimiter);
                String albumTitle = data[1].trim();
                int releaseYear = Integer.parseInt(data[0].trim());
                String artistName = data[2].trim();
                String[] genreNames = data[3].split("\\s*:\\s*");

                System.out.println("An: " + releaseYear);
                System.out.println("Album: " + albumTitle);
                System.out.println("Artist: " + artistName);
                System.out.println("Genuri: ");

                for (String gen : genreNames)
                    System.out.print(gen + "--");

                System.out.println();
                System.out.println("=============");

                insertArtistStatement.setString(1, artistName);
                insertArtistStatement.executeUpdate();

                // Luam ID-ul artistului -> sunt generate automat ca am pus SERIAL la cheia primara id (de asta trebuie sa il aflu in maniera asta, nu il pun eu cu mana in tabel)
                int artistId = -1;
                PreparedStatement selectArtistStatement = connection.prepareStatement(
                        "SELECT id FROM artists WHERE name = ?"
                );
                selectArtistStatement.setString(1, artistName);
                ResultSet artistResult = selectArtistStatement.executeQuery();
                if (artistResult.next()) {
                    artistId = artistResult.getInt("id");
                }

                // Inseram albumul
                insertAlbumStatement.setInt(1, releaseYear);
                insertAlbumStatement.setString(2, albumTitle);
                insertAlbumStatement.setInt(3, artistId);
                insertAlbumStatement.executeUpdate();

                // Luam ID-ul albumului
                int albumId = -1;
                PreparedStatement selectAlbumStatement = connection.prepareStatement(
                        "SELECT id FROM albums WHERE title = ? AND artist_id = ?"
                );
                selectAlbumStatement.setString(1, albumTitle);
                selectAlbumStatement.setInt(2, artistId);
                ResultSet albumResult = selectAlbumStatement.executeQuery();
                if (albumResult.next()) {
                    albumId = albumResult.getInt("id");
                }

                // Inseram genurile si relatiile album-gen (din album_genres)
                for (String genreName : genreNames) {
                    // Inseram genul
                    insertGenreStatement.setString(1, genreName);
                    insertGenreStatement.executeUpdate();

                    // Luam ID-ul genului
                    int genreId = -1;
                    PreparedStatement selectGenreStatement = connection.prepareStatement(
                            "SELECT id FROM genres WHERE name = ?"
                    );
                    selectGenreStatement.setString(1, genreName);
                    ResultSet genreResult = selectGenreStatement.executeQuery();
                    if (genreResult.next()) {
                        genreId = genreResult.getInt("id");
                    }

                    // Inseram relatia album-gen
                    if (albumId != -1 && genreId != -1) {
                        insertAlbumGenreStatement.setInt(1, albumId);
                        insertAlbumGenreStatement.setInt(2, genreId);
                        insertAlbumGenreStatement.executeUpdate();
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

}