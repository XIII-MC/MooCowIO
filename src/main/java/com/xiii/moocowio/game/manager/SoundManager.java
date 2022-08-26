package com.xiii.moocowio.game.manager;

import com.xiii.moocowio.MooCowIO;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {

    public static void customSound(Player p, Sound sound, int volume, int pitch) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            if (p.isOnline()) p.playSound(p.getLocation(), sound, volume, pitch);
        });
    }

    public static void playSound(Player p, int sID) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            if (p.isOnline()) {
                // when exp up
                if (sID == 0) p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                // when level up
                if (sID == 1) p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
                // when die
                if (sID == 2) p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_DEATH, 10, 1);
                // when Progress Saver saves you
                if (sID == 3) p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
                // fr idk
                if (sID == 4) p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 10, 1);
                // when /join
                if (sID == 5) p.playSound(p.getLocation(), Sound.BLOCK_PORTAL_TRIGGER, 10, 1);
                // idk
                if (sID == 6) p.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 10, 1);
                // purchase done
                if (sID == 7) p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 10, 1);
                // gui shop open
                if (sID == 8) p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_TRADING, 10, 1);
                // purchase fail
                if (sID == 9) p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                // Dong (Idk)
                if (sID == 10) p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 10, 1);
                // Click | Hypixel game start sound (Idk)
                if (sID == 11) p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HAT, 10, 1);
                // Ding (Idk)
                if (sID == 12) p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 10, 1);
                // PLING | Notification (Message)
                if (sID == 13) p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 1);

            }
        });
    }
}
