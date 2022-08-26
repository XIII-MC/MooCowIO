package com.xiii.moocowio.game.events.game;

import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.TempData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class GameBuildEvent implements Listener {

    @EventHandler
    public void onGameBuild_Death(PlayerDeathEvent e) {
        TempData data = Data.data.getTempData(e.getEntity().getPlayer());
        if(data.returnBlock != null) {
            for (Map.Entry<Block, Player> entry : data.returnBlock.entrySet()) {
                entry.getKey().setType(Material.AIR);
            }
        }
    }

    @EventHandler
    public void onGameBuild_Quit(PlayerQuitEvent e) {
        TempData data = Data.data.getTempData(e.getPlayer());
        if(data.returnBlock != null) {
            for (Map.Entry<Block, Player> entry : data.returnBlock.entrySet()) {
                entry.getKey().setType(Material.AIR);
            }
        }
    }

    @EventHandler
    public void onGameBuild(BlockPlaceEvent e) {
        TempData data = Data.data.getTempData(e.getPlayer());
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            data.returnBlock.put(e.getBlock(), e.getPlayer());
            if(524 - (data.mapPlacedBlocks + 1) >= 0) data.mapPlacedBlocks += 1;
            if(524 - (data.mapPlacedBlocks) < 0) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§7[§6§lMCI§7] §cYou reached this block's limit!)");
            }
        }
    }

}
