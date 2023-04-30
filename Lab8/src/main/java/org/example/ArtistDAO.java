package org.example;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArtistDAO {
    private Connection connection;

    public ArtistDAO() throws SQLException {
        connection = Database.getConnection();
    }
    public void createArtist(String artistName){
        String sql = "insert into artists (name) values (?)";
        //try with resources -> se inchide automat conexiunea
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, artistName);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Fail la creare artist, niciun rand afectat");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int artistID = generatedKeys.getInt(1);
                System.out.println(artistID);
            } else {
                throw new SQLException("Fail la creare artist, nu am obtinut id");
            }

            //connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Artist findByName(String name, List<Artist> lista) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from artists where name='" + name + "'")) {
            if(rs.next()){
                int idArtist = rs.getInt(1);
                return lista.get(idArtist-1);
            }
            else return null;
        }
    }

    public Artist findById(int id, List<Artist> lista) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select name from artists where id=" + id)){
            if(rs.next()){
                return lista.get(id - 1 );
            }
            else return null;
        }
    }

    public List<Artist> getAll() throws SQLException {
        List <Artist> artistiDinBD = new ArrayList<>();

        String sql = "select name from artists";
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            Artist artistDeAdaugat = new Artist(rs.getString(1));
            artistiDinBD.add(artistDeAdaugat);
        }
        rs.close();
        stmt.close();

        return artistiDinBD;
    }
}
