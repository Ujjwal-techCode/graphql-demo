package com.dailycodebuffer.graphqldemo.controller;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @QueryMapping
    public List<Player> findAll() {
        return playerService.returnAllPLayers();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id) {
        return playerService.findParticularPlayer(id);
    }

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team){
        return playerService.createAPlayerRecord(name, team);
    }

    @MutationMapping
    public Optional<Player> delete(@Argument String name){
        return playerService.deletePlayerRecord(name);
    }

    @MutationMapping
    public Player update(@Argument String name, @Argument Team team){
        return playerService.updatePlayerRecord(name, team);
    }
}