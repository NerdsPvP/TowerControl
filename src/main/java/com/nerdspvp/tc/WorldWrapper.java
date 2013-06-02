package com.nerdspvp.tc;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WorldWrapper {

    private WorldCreator worldCreator;
    private World world;

    private TCInstance instance;

    public List<Sign> signEntities = new ArrayList<Sign>();

    public WorldWrapper(WorldCreator worldCreator, TCInstance instance){
        this.worldCreator = worldCreator;
        this.instance = instance;

    }

    public World getWorld(){
        if(world == null){
            this.world = this.worldCreator.createWorld();
            this.world.setSpawnLocation(-888, 90, 1317);
            findEntities();
        }
        return this.world;
    }

    public void findEntities(){
        List<Block> blocks = new ArrayList<Block>();
        for(int x = this.world.getSpawnLocation().getBlockX() - 100; x <= this.world.getSpawnLocation().getBlockX() + 100; x++)
        {
            for(int y = this.world.getSpawnLocation().getBlockY() - 100; y <= this.world.getSpawnLocation().getBlockY() + 100; y++)
            {
                blocks.add(this.world.getBlockAt(x, 64, y));
            }
        }

        for(Block b : blocks){
            b.getChunk().load(false);

        }

        for(Chunk c : this.world.getLoadedChunks()){
            for(BlockState bs : c.getTileEntities()){
                if(bs instanceof Sign){
                    signEntities.add((Sign) bs);
                    Bukkit.getLogger().info("Sign entity: " + ((Sign) bs).getLine(0));
                }
            }
        }


    }

    public void copyWorld(){
        try {
			String path = Bukkit.getServer().getWorldContainer().getAbsolutePath();

			path = path.substring(0, path.length() - 2);

			File MTCDir = new File(path, "MTC");

			File toDelete = new File(MTCDir.getAbsolutePath() + "/uid.dat");

			if(toDelete.exists())
            	FileUtils.forceDelete(toDelete);
            FileUtils.copyDirectory(MTCDir, new File(Bukkit.getServer().getWorldContainer(), worldCreator.name()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteWorld(){
        for(Player p : getWorld().getPlayers()){
            p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
        }
        Bukkit.getServer().unloadWorld(this.getWorld(), false);
        try {
            FileUtils.deleteDirectory(new File(Bukkit.getServer().getWorldContainer(), worldCreator.name()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
