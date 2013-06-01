package com.nerdspvp.tc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TowerControl extends JavaPlugin {

    public static List<TCInstance> instances = new ArrayList<TCInstance>();

    public static TowerControl towerControl;

    public void onDisable() {

        for(TCInstance s : instances){
            s.getCurrentGame().getWorld().disposeWorld();
        }

    }

    public void onEnable() {
        towerControl = this;

        getServer().getPluginManager().registerEvents(new TCListener(), this);

        instances.add(new TCInstance(20, "Alpha"));
        instances.add(new TCInstance(20, "Beta"));

    }

    public static void prepareForQuit(Player player){
        for(TCInstance instance : instances){
            try {
                instance.getCurrentGame().removePlayerFromTeam(getGamePlayerFromPlayer(player.getName()));
            } catch (Exception e){}
        }
    }

    public static GamePlayer getGamePlayerFromPlayer(String name) throws NullPointerException{
        for(TCInstance instance : instances){
            for(GamePlayer gp : instance.getGamePlayers()){
                if(name.equals(gp.getHandle().getName())){
                    return gp;
                }
            }
        }
        return null;
    }

    public static TCInstance getPlayerInstance(String name) throws NullPointerException{
        for(TCInstance instance : instances){
            for(GamePlayer gp : instance.getGamePlayers()){
                if(name.equals(gp.getHandle().getName())){
                    return instance;
                }
            }
        }
        return null;
    }

    public static void debug(String message){
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            if(p.hasPermission("towercontrol.dev")){
                p.sendMessage(ChatColor.GRAY + "// " + message);
            }
        }
    }



}

