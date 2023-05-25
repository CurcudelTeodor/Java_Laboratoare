package com.example.lab11;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.IdClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controler care expune endpoint-urile pentru jucatori
 * @author Teo
 */
@RestController
@Tag(name = "Player Management", description = "Endpoints fo sho")
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * [POST] Metoda pentru a adauga un jucator in baza de date
     *
     * @param name Numele jucatorului
     * @param color Culoarea cu care va juca jocul
     * @return mesajul Added new player to repo! in caz de succes (s-a adaugat jucatorul in baza de date)
     */
    @PostMapping("/add")
    @Operation(summary = "Adds a player", description = "Adds a player specified by his name and color to the database")
    public String addPlayer(@RequestParam String name, @RequestParam String color) {
        Player player1 = new Player();
        player1.setName(name);
        player1.setColor(color);
        playerRepository.save(player1);
        return "Added new player to repo!";
    }

    /**
     * [GET] Metoda pentru a obtine toti jucatorii din baza de date sub forma unui fisier JSON
     * @return Toti jucatorii din baza de date
     */
    @GetMapping("/list")
    @Operation(summary = "Gets the list of all players", description = "Retrieve the list of players from the database")
    public Iterable<Player> getPlayers() {
        return playerRepository.findAll();
    }

    /**
     * [GET] Metoda pentru a gasi un jucator specific dupa id-ul sau
     * @param id ID-ul jucatorului ce se doreste a fi gasit
     * @return Jucatorul cu ID-ul respectiv sau un mesaj de eroare in caz ca nu exista niciun jucator cu acel ID
     */
    @GetMapping("/find/{id}")
    @Operation(summary = "Finds a player", description = "Finds a player specified by his ID and returns a corresponding message")
    public ResponseEntity<?> findPlayerById(@PathVariable Integer id) {
        try {
            Player player = playerRepository.findPlayerById(id);
            if (player != null) {
                return ResponseEntity.ok(player);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while finding player");
        }
    }

    /**
     * [PUT] Metoda pentru a schimba numele unui jucator identificat prin ID
     * @param id ID-ul jucatorului al carui nume se doreste a fi schimbat
     * @param newName Numele nou pentru jucator
     * @return Un mesaj de succes sau eroare
     */
    @PutMapping(value = "/change/{id}")
    @Operation(summary = "Changes the name of a player", description = "Changes the name of player specified by his ID with a new name given as input")
    public String changeName(@PathVariable Integer id, @RequestParam String newName){
        try{
//        System.out.println("ID:" + id);
//        System.out.println("Newname:" + newName);
        Player playerToBeChanged = playerRepository.findPlayerById(id);
        playerToBeChanged.setName(newName);
        playerRepository.save(playerToBeChanged);
            System.out.println("cxcxc");
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("nnnnnn");
        }

        return "Player's name changed succesfulleh!";
    }

    /**
     * [DELETE] Metoda pentru a sterge un jucator identificat prin ID
     * @param id ID-ul jucatorului ce se doreste a fi sters
     * @return Un mesaj de succes
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a player", description = "Deletes a player specified by his ID from the database")
    public String deletePlayer(@PathVariable Integer id){
        Player playerToBeDeleted = playerRepository.findPlayerById(id);
        playerRepository.delete(playerToBeDeleted);
        return "Deleted the player!";
    }
}
