package com.nerdspvp.tc.signentities;

import com.nerdspvp.tc.TCInstance;
import com.nerdspvp.tc.TowerControl;
import com.nerdspvp.tc.games.StandardGame;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionTowerEntity extends SignEntity {

    public static boolean validate(Sign sign) {
        return sign.getLine(0).equals("triggertower") && sign.getLine(1).equals("potion");
    }

    public void onTrigger(Sign sign, Block triggerer, Player player) {
        TowerControl.debug("Middle tower trigger entity (" + StringUtils.join(sign.getLines(), ";") + ")");
        TCInstance instance = TowerControl.getPlayerInstance(player.getName());
        StandardGame game = (StandardGame)instance.getCurrentGame();
        Block indicator = triggerer.getRelative(BlockFace.DOWN);
        if(isAlreadyCapped(indicator, player)){
            return;
        }


        if(indicator.getType() == Material.IRON_BLOCK){
            instance.broadcast(ChatColor.GRAY + " ➤" + ChatColor.WHITE + " The potion tower has been capped by the " + ChatColor.GRAY + "Iron" + ChatColor.WHITE + " team.");
            game.ironPotionTower = true;
            game.goldPotionTower = false;
        } else if(indicator.getType() == Material.GOLD_BLOCK){
            instance.broadcast(ChatColor.GOLD + " ➤" + ChatColor.WHITE + " The potion tower has been capped by the " + ChatColor.GOLD + "Gold" + ChatColor.WHITE + " team.");
            game.goldPotionTower = true;
            game.ironPotionTower = false;
        }
        for(BlockState bs : sign.getBlock().getChunk().getTileEntities()){
            if(bs instanceof Sign){
                Sign s = (Sign)bs;
                if(PotionTowerEntity.validate(s)){
                    TowerControl.debug("Found valid tower indicator.  Setting to " + indicator.getType().name());
                    setTower(indicator.getType(), s.getBlock().getRelative(BlockFace.DOWN));
                }
            }
        }
    }

    protected boolean isAlreadyCapped(Block indicator, Player player){
        TCInstance instance = TowerControl.getPlayerInstance(player.getName());
        StandardGame game = (StandardGame)instance.getCurrentGame();
        if((indicator.getType() == Material.IRON_BLOCK && game.instanceScoreboard.getTeam("Iron").hasPlayer(player.getPlayer()))
                || (indicator.getType() == Material.GOLD_BLOCK && game.instanceScoreboard.getTeam("Gold").hasPlayer(player.getPlayer()))){
            return true;
        }
        return false;
    }

    protected void setTower(Material toSet, Block origin){
        for(int i = 7; i > 0; i--){
            Location loc = origin.getLocation().subtract(0, i, 0);
            origin.getWorld().getBlockAt(loc).setType(toSet);
        }
    }

}
