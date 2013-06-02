package com.nerdspvp.tc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game  {

    public Scoreboard instanceScoreboard;
    private Team goldTeam;
    private Team ironTeam;

    public boolean goldPotionTower = false;
    public boolean ironPotionTower = false;

    public List<Location> goldSpawn = new ArrayList<Location>();
    public List<Location> ironSpawn = new ArrayList<Location>();

    private WorldWrapper currentWorld;

    public TCInstance parent;

    public Game(TCInstance parent){
        this.instanceScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.parent = parent;
        setupTeams();
        this.currentWorld = new WorldWrapper(new WorldCreator("MTC-" + parent.getInstanceIdentifier()), parent);
    }

    private void findSpawns() {

        for(Sign s : currentWorld.signEntities){
            if(s.getLine(0).equals("goldspawn")){
                goldSpawn.add(s.getLocation().add(0, 3, 0));
                TowerControl.debug("Loaded spawn: " + s.getLocation().toString());
            } else if(s.getLine(0).equals("ironspawn")){
                ironSpawn.add(s.getLocation().add(0,3,0));
                TowerControl.debug("Loaded spawn: " + s.getLocation().toString());
            }
        }

    }

    public Team getIronTeam() {
        return ironTeam;
    }

    public Team getGoldTeam() {
        return goldTeam;
    }


    private void setupTeams(){
        goldTeam = instanceScoreboard.registerNewTeam("Gold");
        goldTeam.setAllowFriendlyFire(false);
        goldTeam.setCanSeeFriendlyInvisibles(true);
        goldTeam.setPrefix(ChatColor.GOLD + "");
        ironTeam = instanceScoreboard.registerNewTeam("Iron");
        ironTeam.setAllowFriendlyFire(false);
        ironTeam.setCanSeeFriendlyInvisibles(true);
        ironTeam.setPrefix(ChatColor.GRAY + "");
    }

    @Override
    public WorldWrapper getWorld() {
        return this.currentWorld;
    }

    private void spawnGold(Player p){
        findSpawns();
        Random rnd = new Random();
        int ourSpawn = rnd.nextInt(goldSpawn.size());
        p.teleport(goldSpawn.get(ourSpawn));
    }

    private void spawnIron(Player p){
        findSpawns();
        Random rnd = new Random();
        int ourSpawn = rnd.nextInt(ironSpawn.size());
        p.teleport(ironSpawn.get(ourSpawn));
    }

    public void spawnPlayer(Player p){
        if(goldTeam.hasPlayer(p)){
            spawnGold(p);
        } else if(ironTeam.hasPlayer(p)){
            spawnIron(p);
        }
    }

    public void addPlayerToTeam(GamePlayer gp, String teamIdentifier) {
        spawnPlayer(gp.getHandle());
        if(teamIdentifier.equalsIgnoreCase("gold")){
            goldTeam.addPlayer(gp.getHandle());

        } else {
            ironTeam.addPlayer(gp.getHandle());

        }
    }

    public void removePlayerFromTeam(GamePlayer gp) {
        ironTeam.removePlayer(gp.getHandle());
        goldTeam.removePlayer(gp.getHandle());
    }


}
