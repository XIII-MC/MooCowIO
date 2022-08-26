package com.xiii.moocowio.game.events.lobby;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.PlayerData;
import com.xiii.moocowio.core.data.ServerData;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.game.manager.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyJoinEvent implements Listener {

    @EventHandler
    public void onLobbyJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPermission("MCI.STAFF")) e.setJoinMessage("");
        if(e.getPlayer().hasPermission("MCI.STAFF")) {
            for (Player p : Bukkit.getOnlinePlayers())
                if (p.hasPermission("MCI.STAFF"))
                    p.sendMessage("§4§lSTAFF §7» §8" + e.getPlayer().getName() + " §7joined");
                else e.setJoinMessage("§7(§2§L+§7) §f" + e.getPlayer().getName());
        }
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            Data.data.clearPlayerTemp(e.getPlayer());
            Data.data.registerUser(e.getPlayer());
            ServerData server = Data.data.getServerData(Bukkit.getServer());
            PlayerData player = Data.data.getUserData(e.getPlayer());
            TempData data = Data.data.getTempData(e.getPlayer());
            e.getPlayer().getInventory().clear();
            SoundManager.playSound(e.getPlayer(), 3);
            e.getPlayer().sendTitle("§e§k|||§r §6§LWelcome §e§k|||", "§fHello there, §b" + e.getPlayer().getName());
            if (player.saveProgress) {
                data.isInGame = false;
                e.getPlayer().getInventory().clear();
                e.getPlayer().sendMessage("§8§M+---------------------------------------+");
                e.getPlayer().sendMessage("      §fHello there, §b" + e.getPlayer().getName());
                e.getPlayer().sendMessage("   §fYour previous progress was §a§nsaved");
                e.getPlayer().sendMessage("   §fHere is the list of the saved progress:");
                e.getPlayer().sendMessage("");
                e.getPlayer().sendMessage("   §a+ " + player.woodAmount + " Wood");
                e.getPlayer().sendMessage("   §a+ " + player.stoneAmount + " Stone");
                e.getPlayer().sendMessage("   §a+ " + player.foodAmount + " Food");
                e.getPlayer().sendMessage("   §6+ " + player.goldAmount + " Gold");
                e.getPlayer().sendMessage("   §a+ " + player.currentExp + " Exp / " + player.currentAge + " Level");
                e.getPlayer().sendMessage("   §c- Position not saved.");
                e.getPlayer().sendMessage("   §c- Placed blocks not saved.");
                e.getPlayer().sendMessage("");
                e.getPlayer().sendMessage("   §B§lINFO: §fYour progress will always be saved when");
                e.getPlayer().sendMessage("   §fleaving with a Progress Saver. Your Progress Saver is");
                e.getPlayer().sendMessage("   §fstill active and working.");
                e.getPlayer().sendMessage("§8§M+---------------------------------------+");
            } else {
                e.getPlayer().sendMessage("§8§M+---------------------------------------+");
                e.getPlayer().sendMessage("       §fHello there, §b" + e.getPlayer().getName());
                e.getPlayer().sendMessage("   §fYour previous progress was §c§nnot saved");
                e.getPlayer().sendMessage("   §fYou can buy an upgrade to save it next time!");
                e.getPlayer().sendMessage("");
                e.getPlayer().sendMessage("   §6  §8- §6Progress Saver (x1) - 10,000g");
                e.getPlayer().sendMessage("");
                e.getPlayer().sendMessage("   §fYou can buy it at the §b/shop");
                e.getPlayer().sendMessage("§8§M+---------------------------------------+");
            }
            Bukkit.getScheduler().runTask(MooCowIO.instance, () -> e.getPlayer().teleport(server.lobbySpawn));
        });
    }

}
