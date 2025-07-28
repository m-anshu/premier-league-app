package com.pl.premier_zone.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController // this marks the class as a spring mvc controller
@RequestMapping(path="api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired //allows the controller to delegate the business logic back to the service layer
    public PlayerController(PlayerService playerService){
        this.playerService=playerService;
    }

    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required=false) String team,
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String position,
            @RequestParam(required=false) String nation ){
        if(team!=null && position!=null){
            return playerService.getPlayersByTeamAndPosition(team,position);
        }
        else if(team!=null){
            return playerService.getPlayersFromTeam(team);
        }
        else if(name!=null){
            return playerService.getPlayersByName(name);
        }
        else if(position!=null){
            return playerService.getPlayersByPos(position);
        }
        else if(nation!=null){
            return playerService.getPlayersByNation(nation);
        }
        else{
            return playerService.getPlayers();
        }
    }

    @PostMapping //handles HTTP POST requests to add players to our database
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player createdPlayer =playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer,HttpStatus.CREATED); //this means when a player is created it will return a status code of 201 if it is fully successful
    }

    @PutMapping //updating existing players
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        Player resultPlayer =playerService.updatePlayer(player);
        if(resultPlayer!=null){
            return new ResponseEntity<>(resultPlayer,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //indicates that the Player was not found in the repository of the database
        }
    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("player deleted successfully",HttpStatus.OK);
    }
}
