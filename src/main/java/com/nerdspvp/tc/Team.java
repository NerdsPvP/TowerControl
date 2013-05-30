package com.nerdspvp.tc;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private int color;

    private List<GamePlayer> gamePlayers = new ArrayList<GamePlayer>();

    public Team(String name, int color){
        this.name = name;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void addPlayerToTeam(GamePlayer gamePlayer){
        this.gamePlayers.add(gamePlayer);
    }
}
