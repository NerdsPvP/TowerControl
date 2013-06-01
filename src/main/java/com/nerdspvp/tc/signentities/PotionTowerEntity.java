package com.nerdspvp.tc.signentities;

import com.nerdspvp.tc.TCInstance;
import com.nerdspvp.tc.TowerControl;
import com.nerdspvp.tc.games.StandardGame;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
        TCInstance instance = TowerControl.getPlayerInstance(player.getName());
        StandardGame game = (StandardGame)TowerControl.getPlayerInstance(player.getName()).getCurrentGame();
        Block indicator = triggerer.getRelative(BlockFace.DOWN);
        if(isAlreadyCapped(indicator, player)){
            return;
        }
        instance.broadcast(ChatColor.DARK_RED + "The center tower has been capped!");
        if(indicator.getType() == Material.IRON_BLOCK){
            game.ironPotionTower = true;
            game.goldPotionTower = false;
        } else if(indicator.getType() == Material.GOLD_BLOCK){
            game.goldPotionTower = true;
            game.ironPotionTower = false;
        }
    }

    private boolean isAlreadyCapped(Block indicator, Player player){
        TCInstance instance = TowerControl.getPlayerInstance(player.getName());
        StandardGame game = (StandardGame)instance.getCurrentGame();
        if((indicator.getType() == Material.IRON_BLOCK && game.instanceScoreboard.getTeam("Iron").hasPlayer(player.getPlayer()))
                || (indicator.getType() == Material.GOLD_BLOCK && game.instanceScoreboard.getTeam("Gold").hasPlayer(player.getPlayer()))){
            return true;
        }
        return false;
    }

}
