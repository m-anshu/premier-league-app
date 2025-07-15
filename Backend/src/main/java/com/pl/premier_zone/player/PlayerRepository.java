package com.pl.premier_zone.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //indicates that the interface is a spring data repository
public interface PlayerRepository extends JpaRepository<Player,String> { //this interface extension lets us add CRUD functionality to the Entity , here String refers to the primary key of the Entity Player
    void deleteByName(String playerName);
    Optional<Player> findByName(String name); // allows us to find any player by their name in the Repository , Optional is used to handle cases where a Player might not be found in the Repository
}
