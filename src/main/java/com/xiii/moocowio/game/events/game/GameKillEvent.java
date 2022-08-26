package com.xiii.moocowio.game.events.game;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.game.manager.BackpackManager;
import com.xiii.moocowio.game.manager.LevelManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class GameKillEvent implements Listener {

    @EventHandler
    public void onGameKill(EntityDeathEvent e) {
        if(e.getEntity() != null) e.setDroppedExp(0); e.getDrops().clear();
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            if (e.getEntity().getKiller() != null && e.getEntity() != null) {
                if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {
                    if (e.getEntity().getType() != EntityType.PLAYER) {
                        BackpackManager.updateBackpack(e.getEntity().getKiller(), "GOLD", "ADD", 10);
                        LevelManager.updateLevel(e.getEntity().getKiller());
                        e.getEntity().getKiller().sendMessage("§7[§6§lMCI§7] §6+10g §8| §eEntity kill");
                    }
                }
            }

        });
    }

}
