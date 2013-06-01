package com.nerdspvp.tc.signentities;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionTowerEntity extends SignEntity {

    public static boolean validate(Sign sign) {
        return sign.getLine(0).equals("triggertower") && sign.getLine(1).equals("potion");
    }

    public void onTrigger(Sign sign, Block triggerer) {
        ThrownPotion speed = (ThrownPotion)triggerer.getWorld().spawnEntity(triggerer.getLocation(), EntityType.SPLASH_POTION);
        speed.getEffects().clear();
        speed.getEffects().add(new PotionEffect(PotionEffectType.SPEED, 900, 1));
        speed.getEffects().add(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));

    }

}
