package com.nerdspvp.tc;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TCInstance {

    private final int maxPlayers;
    private final String instanceIdentifier;

    private Game currentGame;

    private List<GamePlayer> gamePlayers = new ArrayList<GamePlayer>();

    public TCInstance(int maxPlayers, String instanceIdentifier, Game currentGame) {
        this.maxPlayers = maxPlayers;
        this.instanceIdentifier = instanceIdentifier;
        this.currentGame = currentGame;
    }

    public List<GamePlayer> getGamePlayers(){
        return gamePlayers;
    }

    public void addPlayer(Player player){
        gamePlayers.add(new GamePlayer(player));
    }

    public String getInstanceIdentifier(){
        return this.instanceIdentifier;
    }

    public int getMaxPlayers(){
        return this.maxPlayers;
    }

    public Game getCurrentGame(){
        return this.currentGame;
    }

}
