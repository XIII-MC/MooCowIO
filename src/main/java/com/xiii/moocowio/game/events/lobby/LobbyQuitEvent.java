package com.xiii.moocowio.game.events.lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyQuitEvent implements Listener {

    @EventHandler
    public void onLobbyQuit(PlayerQuitEvent e) {
        if (e.getPlayer().hasPermission("MCI.STAFF")) e.setQuitMessage("");
        if(e.getPlayer().hasPermission("MCI.STAFF")) {
            for (Player p : Bukkit.getOnlinePlayers())
                if (p.hasPermission("MCI.STAFF")) p.sendMessage("§4§lSTAFF §7» " + e.getPlayer().getName() + " §7left");
                else e.setQuitMessage("§7(§4§L+§7) §f" + e.getPlayer().getName());
        }
    }

}
