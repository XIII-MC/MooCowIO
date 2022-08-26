package com.xiii.moocowio.game.manager;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.TempData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LevelManager {
    static double math;

    public static void updateLevel(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            TempData data = Data.data.getTempData(p);
            data.currentExp += 10;
            SoundManager.playSound(p, 0);
            math = data.currentExp / (data.requiredExp / 100);
            data.expBar += math;
            p.setExp((float) (math / 100));
            if (data.currentExp >= data.requiredExp) {
                data.currentExp = 0;
                data.currentAge += 1;
                p.setLevel(data.currentAge);
                p.setExp(0f);
                SoundManager.playSound(p, 1);
                p.sendTitle("§2§lꜛ §a§l§nLEVEL UP§r §2§lꜛ", "§b" + (data.currentAge - 1) + " §f➩ §b" + data.currentAge);
                data.requiredExp = (data.currentAge * 100);
            }
        });
    }

}
