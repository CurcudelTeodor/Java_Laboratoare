package com.example.clientapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/client")
public class GameClientController {

    private final String BASE_URL = "http://localhost:8084/games";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/games")
    public String listGames(Model model) {
        List<Game> games = restTemplate.getForObject(BASE_URL + "/list", List.class);
        System.out.println(games.get(0));
        System.out.println(games.get(1));
        System.out.println(games.get(2));
        model.addAttribute("games", games);
        return "listGames";
    }
}
