package com.xiii.moocowio.game.events.game;

import com.xiii.moocowio.MooCowIO;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class GameRespawnEvent implements Listener {

    @EventHandler
    public void onGameRespawn(PlayerRespawnEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
           e.getPlayer().getInventory().clear();
        });
    }
}
