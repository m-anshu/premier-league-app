package com.pl.premier_zone.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component //is a Spring annotation that marks a java class as a Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired//this injects this Player Repository bean into the Service which will enable it to interact with the database
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository=playerRepository;
    }

    //now for the functions that are the actual business logic , for now just focusing on GET capabilities
    public List<Player> getPlayers(){
        return playerRepository.findAll(); //simply returns a list of all players from the database table
    }

    public List<Player> getPlayersFromTeam(String teamName){
        return playerRepository.findAll().stream()
                .filter(player -> teamName.equals(player.getTeam()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String searchText){
        return playerRepository.findAll().stream()
                .filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPos(String searchText){
        return playerRepository.findAll().stream()
                .filter(player->player.getPos().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player>  getPlayersByNation(String searchText){
        return playerRepository.findAll().stream()
                .filter(player->player.getNation().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByTeamAndPosition(String team,String position){
        return playerRepository.findAll().stream()
                .filter(player->team.equals(player.getTeam()) && position.equals(player.getPos()))
                .collect(Collectors.toList());
    }

    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer=playerRepository.findByName(updatedPlayer.getName());

        if(existingPlayer.isPresent()){//isPresent() indicates a check whether the Player is present in the database
            Player playerToUpdate=existingPlayer.get();
            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setTeam(updatedPlayer.getTeam());
            playerToUpdate.setPos(updatedPlayer.getPos());
            playerToUpdate.setNation(updatedPlayer.getNation());

            playerRepository.save(playerToUpdate);
            return  playerToUpdate;
        }
        return null; //if nothing is found in the database
    }

    @Transactional //means that data integrity is maintained during this delete operation
    public void deletePlayer(String playerName){
        playerRepository.deleteByName(playerName);
    }

}
