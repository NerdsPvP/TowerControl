package com.nerdspvp.tc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GamePlayer {

    private String playerName;
    private TCInstance tcInstance;

    public GamePlayer(Player player, TCInstance instance){
        this.playerName = player.getName();
        this.tcInstance = instance;
    }

    public Player getHandle(){
        return Bukkit.getServer().getPlayerExact(this.playerName);
    }

    public TCInstance getTcInstance() {
        return tcInstance;
    }

	public String getPlayerName(){
		return playerName;
	}
}
