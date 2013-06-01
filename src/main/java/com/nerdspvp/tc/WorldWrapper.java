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
            this.world = prepareWorld();
        }
        return this.world;
    }

    public World prepareWorld(){
        try {
            FileUtils.forceDelete(
                    new File(new File(
                            Bukkit.getServer().getWorldContainer(),
                            worldCreator.name()
                    ), "uid.dat")

            );

            FileUtils.copyDirectory(
                new File(
                        Bukkit.getServer().getWorldContainer(),
                        worldCreator.name()
                ),
                new File(
                        Bukkit.getServer().getWorldContainer(),
                        worldCreator.name() + "-" + instance.getInstanceIdentifier()
                )
            );
        } catch (FileNotFoundException e){

        } catch (Exception e){
            e.printStackTrace();
        }
        WorldCreator wc = new WorldCreator(worldCreator.name() + "-" + instance.getInstanceIdentifier());
        World w = wc.createWorld();
        w.setSpawnLocation(-888, 102, 1340);

        List<Block> blocks = new ArrayList<Block>();
        for(int x = w.getSpawnLocation().getBlockX() - 100; x <= w.getSpawnLocation().getBlockX() + 100; x++)
        {
            for(int y = w.getSpawnLocation().getBlockY() - 100; y <= w.getSpawnLocation().getBlockY() + 100; y++)
            {
                blocks.add(w.getBlockAt(x, 64, y));
            }
        }


        for(Block b : blocks){
            b.getChunk().load(false);
        }


        for(Chunk c : w.getLoadedChunks()){
            for(BlockState bs : c.getTileEntities()){
                if(bs instanceof Sign){
                    Sign s = (Sign)bs;
                    signEntities.add(s);
                }
            }
        }
        return w;
    }

    public void disposeWorld(){
        for(Player p : getWorld().getPlayers()){
            p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
        }
        Bukkit.getServer().unloadWorld(getWorld(), false);
        try {
            FileUtils.deleteDirectory(
                    new File(
                            Bukkit.getServer().getWorldContainer(),
                            worldCreator.name() + "-" + instance.getInstanceIdentifier()
                    )
            );
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
