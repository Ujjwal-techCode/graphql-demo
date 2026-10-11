package com.dailycodebuffer.graphqldemo.service;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> playerList = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        playerList.add(new Player(id.incrementAndGet(), "MSD", Team.CSK));
        playerList.add(new Player(id.incrementAndGet(), "Rohit", Team.MI));
        playerList.add(new Player(id.incrementAndGet(), "Pandya", Team.GT));
        playerList.add(new Player(id.incrementAndGet(), "Rahul", Team.LSG));
        playerList.add(new Player(id.incrementAndGet(), "Virat", Team.RCB));
    }

    public List<Player> returnAllPLayers() {
        return playerList;
    }

    public Optional<Player> findParticularPlayer(Integer id) {
        return playerList.stream().filter(player-> player.Id()==id).findFirst();
    }

    public Player createAPlayerRecord(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        playerList.add(player);
        return player;
    }

    public Optional<Player> deletePlayerRecord(String name) {
        Optional<Player> player = playerList.stream().filter(playr-> playr.name().equalsIgnoreCase(name)).findFirst();
        if(player.isPresent()){
            playerList.remove(player.get());
        }
        return player;
    }

    public Player updatePlayerRecord(String name, Team team) {
        Optional<Player> player = playerList.stream().filter(playr -> playr.name().equalsIgnoreCase(name)).findFirst();
        Player removedPlayer = null;
        if (player.isPresent()) {
            int index = playerList.indexOf(player.get());
            removedPlayer = playerList.set(index, new Player(player.get().Id(), name, team));
        }
        return removedPlayer;
    }
}
