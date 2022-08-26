package com.xiii.moocowio.game.commands;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.game.displays.ShopDisplay;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGUI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getScheduler().runTask(MooCowIO.instance, () -> {

            Player s = (Player) sender;
            TempData player = Data.data.getTempData(s);

            if (command.getName().equalsIgnoreCase("shop")) {
                if (player.isInGame) {
                    s.closeInventory();
                    ShopDisplay.shopGUI(s);
                }
            }

        });
        return true;
    }
}
