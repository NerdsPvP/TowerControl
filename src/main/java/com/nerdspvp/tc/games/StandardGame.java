package com.nerdspvp.tc.games;

import com.nerdspvp.tc.Game;
import com.nerdspvp.tc.GamePlayer;
import com.nerdspvp.tc.TCInstance;
import com.nerdspvp.tc.WorldWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class StandardGame extends Game {

    public Scoreboard instanceScoreboard;
    private Team goldTeam;
    private Team ironTeam;

    public boolean goldPotionTower = false;
    public boolean ironPotionTower = false;

    public TCInstance parent;

    public StandardGame(TCInstance parent){
        this.instanceScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.parent = parent;
        setupTeams();

    }

    public Team getIronTeam() {
        return ironTeam;
    }

    public Team getGoldTeam() {
        return goldTeam;
    }


    private void setupTeams(){
        goldTeam = instanceScoreboard.registerNewTeam(parent.getInstanceIdentifier() + "-Gold");
        goldTeam.setAllowFriendlyFire(false);
        goldTeam.setCanSeeFriendlyInvisibles(true);
        goldTeam.setPrefix(ChatColor.GOLD + "");
        ironTeam = instanceScoreboard.registerNewTeam(parent.getInstanceIdentifier() + "-Iron");
        ironTeam.setAllowFriendlyFire(false);
        ironTeam.setCanSeeFriendlyInvisibles(true);
        ironTeam.setPrefix(ChatColor.GRAY + "");
    }

    @Override
    public WorldWrapper getWorld() {
        return null;
    }

    @Override
    public void addPlayerToTeam(GamePlayer gp, String teamIdentifier) {
        if(teamIdentifier.equals("gold")){
            goldTeam.addPlayer(gp.getHandle());
        } else {
            ironTeam.addPlayer(gp.getHandle());
        }
    }

    @Override
    public void removePlayerFromTeam(GamePlayer gp) {
        ironTeam.removePlayer(gp.getHandle());
        goldTeam.removePlayer(gp.getHandle());
    }


}
