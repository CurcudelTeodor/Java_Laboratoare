package com.example.lab11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/add")
    public String addPlayer(@RequestParam String name, @RequestParam String color) {
        Player player1 = new Player();
        player1.setName(name);
        player1.setColor(color);
        playerRepository.save(player1);
        return "Added new player to repo!";
    }

    @GetMapping("/list")
    public Iterable<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Player findPlayerById(@PathVariable Integer id) {
        return playerRepository.findPlayerById(id);
    }
}
