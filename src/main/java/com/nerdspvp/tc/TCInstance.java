package com.nerdspvp.tc;

import com.nerdspvp.tc.games.StandardGame;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TCInstance {

    private final int maxPlayers;
    private final String instanceIdentifier;

    private Game currentGame;

    private List<GamePlayer> gamePlayers = new ArrayList<GamePlayer>();

    public TCInstance(int maxPlayers, String instanceIdentifier) {
        this.maxPlayers = maxPlayers;
        this.instanceIdentifier = instanceIdentifier;
        this.currentGame = new StandardGame(this);
    }

    public List<GamePlayer> getGamePlayers(){
        return gamePlayers;
    }

    public void addPlayer(Player player, String team){
        GamePlayer gamePlayer = new GamePlayer(player, this);
        gamePlayers.add(gamePlayer);
        currentGame.addPlayerToTeam(gamePlayer, team);
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

    public void broadcast(String string){
        for(GamePlayer gp : gamePlayers){
            gp.getHandle().sendMessage(string);
        }
    }

}
