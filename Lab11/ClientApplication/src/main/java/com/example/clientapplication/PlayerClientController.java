package com.example.clientapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/client-players")
public class PlayerClientController {

    private final String BASE_URL = "http://localhost:8084/players";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/list")
    public String listPlayers(Model model) {
        List<Player> players = restTemplate.getForObject(BASE_URL + "/list", List.class);
        model.addAttribute("players", players);
        return "listPlayers";
    }

    @GetMapping("/find/{id}")
    public String findPlayerById(@PathVariable Integer id, Model model) {
        String url = BASE_URL + "/find/" + id;
        try {
            Player player = restTemplate.getForObject(url, Player.class);
            model.addAttribute("player", player);
        } catch (HttpClientErrorException.NotFound ex) {
            System.out.println("addfsadffs");
            model.addAttribute("errorMessage", "Player not found");
        }
        return "playerDetails";
    }


    @GetMapping("/add")
    public String showAddPlayerForm() {
        return "addPlayer";
    }

    @PostMapping("/add")
    public String addPlayer(@RequestParam String name, @RequestParam String color) {
        Player player = new Player();
        player.setName(name);
        player.setColor(color);

        ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/add?name="+name+"&color="+color, player, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            // Player added successfully, display success message or redirect
            return "redirect:/client-players/list";
        } else {
            // Error occurred, handle accordingly
            return "error";
        }
    }

    @PutMapping("/change/{id}")
    @ResponseBody
    public void changePlayerName(@PathVariable Integer id, @RequestParam String newName) {
        try{
            String url = BASE_URL + "/change/" + id + "?newName=" + newName;
            restTemplate.put(url, null);
            System.out.println("Player's name changed successfully!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deletePlayer(@PathVariable Integer id) {
        String url = BASE_URL + "/delete/" + id;
        restTemplate.delete(url);
        System.out.println("Player deleted successfully!");
    }
}
