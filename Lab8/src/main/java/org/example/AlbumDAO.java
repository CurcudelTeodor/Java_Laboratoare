package org.example;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    private Connection connection;

    public AlbumDAO() throws SQLException {
        connection = Database.getConnection();
    }

    public void createAlbum(Album album) throws SQLException {
        String sql = "insert into albums (release_year, title, artist_id) values (?,?,?)";
        //try with resources -> se inchide automat conexiunea
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, album.getReleaseYear());
            statement.setString(2, album.getTitle());
            statement.setInt(3, album.getArtistID());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Fail la creare album, niciun rand afectat");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
                int albumId = generatedKeys.getInt(1);
                insertAlbumGenres(albumId, album.getGenreIds());
            } else {
                throw new SQLException("Fail la creare album, nu am obtinut id");
            }

            //connection.commit();
        }
    }

    private void insertAlbumGenres(int albumID, int [] genreIDs) throws SQLException{
        String sql = "insert into album_genres (album_id, genre_id) values (?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            for(int gen_id : genreIDs){
                statement.setInt(1,albumID);
                statement.setInt(2,gen_id);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public List<Album> getAll() throws SQLException {
        List <Album> albums = new ArrayList<>();
        String sql = "select * from albums";
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            int releaseYear = rs.getInt("release_year");
            String title = rs.getString("title");
            int artistID = rs.getInt("artist_id");

            //vedem ce genuri are albumul
            int albumID = rs.getInt("id");
            List<Integer> genreIds = new ArrayList<>();
            String sql2 = "select genre_id from album_genres where album_id = ?";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setInt(1,albumID);
            ResultSet rs2 = stmt2.executeQuery();
            while(rs2.next()){
                genreIds.add(rs2.getInt("genre_id"));
            }
            Album album = new Album(releaseYear,title,artistID,genreIds.stream().mapToInt(Integer::intValue).toArray());
            albums.add(album);
            stmt2.close();
        }
        rs.close();
        stmt.close();
        return albums;
    }



    /*
    public void update(Album album) throws SQLException {
        String sql = "UPDATE albums SET release_year = ?, title = ?, artist = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, album.getReleaseYear());
        stmt.setString(2, album.getTitle());
        stmt.setString(3, album.getArtist());
        stmt.setInt(4, album.getId());

        stmt.executeUpdate();

        int albumId = album.getId();
        int[] genreIds = album.getGenreIds();
        String sql2 = "DELETE FROM album_genre WHERE album_id = ?";
        PreparedStatement stmt2 = connection.prepareStatement(sql2);
        stmt2.setInt(1, albumId);
        stmt2.executeUpdate();
        String sql3 = "INSERT INTO album_genre (album_id, genre_id) VALUES (?, ?)";
        PreparedStatement stmt3 = connection.prepareStatement(sql3);
        for (int genreId : genreIds) {
            stmt3.setInt(1, albumId);
            stmt3.setInt(2, genreId);
            stmt3.executeUpdate();
        }

        stmt.close();
        stmt2.close();
        stmt3.close();
    }
    public void delete(int id) {

        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {
            String sql1 = "DELETE FROM albums WHERE id = ?";
            stmt1 = connection.prepareStatement(sql1);
            stmt1.setInt(1, id);
            int rowsDeleted = stmt1.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Album with id " + id + " not found");
            }

            String sql2 = "DELETE FROM album_genre WHERE album_id = ?";
            stmt2 = connection.prepareStatement(sql2);
            stmt2.setInt(1, id);
            stmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null) {
                    stmt1.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     */
}
