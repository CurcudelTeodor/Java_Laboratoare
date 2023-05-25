package com.example.lab11;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findPlayerById(Integer id);
}
