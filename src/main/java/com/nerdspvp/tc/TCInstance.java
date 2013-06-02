package com.nerdspvp.tc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TCInstance {

    private final int maxPlayers;
    private final String instanceIdentifier;

    private Game currentGame;

    private List<GamePlayer> gamePlayers = new ArrayList<GamePlayer>();

	private TowerControl plugin;

    public TCInstance(int maxPlayers, String instanceIdentifier, TowerControl tc) {
		this.plugin = tc;
        this.maxPlayers = maxPlayers;
        this.instanceIdentifier = instanceIdentifier;
        this.currentGame = new Game(this);
    }

    public List<GamePlayer> getGamePlayers(){
        return gamePlayers;
    }

    public void addPlayer(final Player player, String team){
        GamePlayer gamePlayer = new GamePlayer(player, this);
        gamePlayers.add(gamePlayer);
        currentGame.addPlayerToTeam(gamePlayer, team);
        broadcast(ChatColor.BLUE + " + " + ChatColor.WHITE + player.getName() + " is joining the " + team + " team.");

		final Game game = this.currentGame;

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				player.teleport(game.getWorld().getWorld().getSpawnLocation());
			}
		});
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

	public TowerControl getPlugin(){
		return plugin;
	}
}
