package com.example.lab11;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * Controler care expune endpoint-urile pentru jocuri
 * @author Teo
 */
@RestController
@RequestMapping("/games")
public class GameController {
    private final JdbcTemplate jdbcTemplate;

    public GameController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * [GET] Metoda pentru a obtine toate jocurile din baza de date
     * @return Jocurile sub formatul : id_game, id_player, x_coordinate, y_coordinate, symbol
     */
    @GetMapping("/list")
    @Operation(summary = "Gets the list of all the games", description = "Gets the list of all the games from the database")
    public Iterable<Game> getAllGames() {
        String query = "SELECT * FROM GoGame";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Game game = new Game();
            game.setGameId(rs.getInt("id_game"));
            game.setPlayerId(rs.getInt("id_player"));
            game.setxCoordinate(rs.getInt("x_coordinate"));
            game.setyCoordinate(rs.getInt("y_coordinate"));
            String symbol = rs.getString("symbol");
            if (symbol != null && symbol.length() > 0) {
                game.setSymbol(symbol.charAt(0));
            }
            System.out.print(game.getGameId());
            System.out.print(game.getPlayerId());
            System.out.print(game.getxCoordinate());
            System.out.print(game.getyCoordinate());
            System.out.println(game.getSymbol());
            System.out.println("-------------------");



            return game;
        });
    }
}
