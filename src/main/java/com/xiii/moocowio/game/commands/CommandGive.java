package com.xiii.moocowio.game.commands;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.game.manager.BackpackManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGive implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {

            if (command.getName().equalsIgnoreCase("boost")) {
                if (args.length < 4)
                    sender.sendMessage("§7[§6§lMCI§7] §cError! Usage: /boost <Player> <Wood/Stone/Food/Gold> (Amount) (SET/ADD)");
                if (args.length == 4) {
                    if (args[3].contentEquals("ADD")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        BackpackManager.updateBackpack(target, args[1], "ADD", Integer.parseInt(args[2]));
                    } else if(args[3].equalsIgnoreCase("SET")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        BackpackManager.updateBackpack(target, args[1], "SET", Integer.parseInt(args[2]));
                    }
                }
            }
        });
        return true;
    }
}
