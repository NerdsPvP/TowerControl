package com.nerdspvp.tc;

import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldWrapper {

    private WorldCreator worldCreator;
    private World world;

    public void WorldWrapper(WorldCreator worldCreator){
        this.worldCreator = worldCreator;
    }

    public World getWorld(){
        if(world == null){
            this.world = this.worldCreator.createWorld();
        }
        return this.world;
    }

}
