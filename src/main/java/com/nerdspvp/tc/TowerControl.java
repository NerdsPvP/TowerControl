package com.nerdspvp.tc;

import com.nerdspvp.tc.games.StandardGame;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TowerControl extends JavaPlugin {

    public static List<TCInstance> instances = new ArrayList<TCInstance>();

    public void onDisable() {
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TCListener(), this);

        instances.add(new TCInstance(20, "Alpha", new StandardGame()));
    }

}

