package org.example;

import java.sql.*;

public class GameDAO {
    private final String url;
    private final String user;
    private final String password;

    public GameDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

//    public void createGame(int gameId) {
//        try (Connection conn = DriverManager.getConnection(url, user, password)) {
//            String sql = "INSERT INTO GoGame (id) VALUES (?)";
//            try (PreparedStatement statement = conn.prepareStatement(sql)) {
//                statement.setInt(1, gameId);
//                statement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public boolean joinGame(int gameId, int playerId) {
//        try (Connection conn = DriverManager.getConnection(url, user, password)) {
//            String sql = "SELECT COUNT(*) FROM GoGame WHERE id = ?";
//            try (PreparedStatement statement = conn.prepareStatement(sql)) {
//                statement.setInt(1, gameId);
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    if (resultSet.next()) {
//                        int playerCount = resultSet.getInt(1);
//                        if (playerCount < 2) {
//                            sql = "INSERT INTO GoGame (id, player) VALUES (?, ?)";
//                            try (PreparedStatement joinStatement = conn.prepareStatement(sql)) {
//                                joinStatement.setInt(1, gameId);
//                                joinStatement.setInt(2, playerId);
//                                joinStatement.executeUpdate();
//                                return true;
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public void placePiece(int gameId, long playerID, int xCoordinate, int yCoordinate, char symbol) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO GoGame (id_game, id_player, x_coordinate, y_coordinate, symbol) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, gameId);
                statement.setLong(2, playerID); // playerID = id-ul threadului
                statement.setInt(3, xCoordinate);
                statement.setInt(4, yCoordinate);
                statement.setString(5, String.valueOf(symbol));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getHighestGameId() {
        int highestId = 0;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT MAX(id_game) AS max_id FROM goGame";

            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    highestId = resultSet.getInt("max_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return highestId;
    }
}
