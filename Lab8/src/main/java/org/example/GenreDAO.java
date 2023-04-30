package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {

    private Connection connection;

    public GenreDAO() throws SQLException {
        connection = Database.getConnection();
    }

    public void createGenre(String genreName) {
        String sql = "insert into genres (name) values (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, genreName);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Fail la creare album, niciun rand afectat");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            System.out.println(generatedKeys.getInt(1));

            //connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Genre findByName(String name, List<Genre> lista) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from genres where name='" + name + "'")) {
            if(rs.next()){
                int idArtist = rs.getInt(1);
                return lista.get(idArtist-1);
            }
            else return null;
        }
    }

    public Genre findById(int id, List<Genre> lista) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select name from genres where id=" + id)){
            if(rs.next()){
                return lista.get(id - 1 );
            }
            else return null;
        }
    }

    public List<Genre> getAll() throws SQLException {
        List <Genre> genuriDinBD = new ArrayList<>();

        String sql = "select name from genres";
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            Genre genDeAdaugat = new Genre(rs.getString(1));
            genuriDinBD.add(genDeAdaugat);
        }
        rs.close();
        stmt.close();

        return genuriDinBD;
    }
}
