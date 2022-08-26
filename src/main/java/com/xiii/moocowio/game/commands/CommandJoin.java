package com.xiii.moocowio.game.commands;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.PlayerData;
import com.xiii.moocowio.core.data.ServerData;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.game.manager.BackpackManager;
import com.xiii.moocowio.game.manager.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CommandJoin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {

            PlayerData data = Data.data.getUserData((Player) sender);
            TempData player = Data.data.getTempData((Player) sender);
            ServerData server = Data.data.getServerData(Bukkit.getServer());

            if (command.getName().equalsIgnoreCase("join")) {
                if (!player.isInGame) {
                    if (data.isBanned) {
                        sender.sendMessage("§8§M+---------------------------------------+");
                        sender.sendMessage("   §4§LBANNED");
                        sender.sendMessage("");
                        sender.sendMessage("   §cYou were banned from MooCowIO");
                        sender.sendMessage("   §cYou are now unable to play.");
                        sender.sendMessage("   §cThis ban will not expire.");
                        sender.sendMessage("   §fIf you think this ban was unfair, please appeal here:");
                        sender.sendMessage("      §bdiscord.gg/MCIMC");
                        sender.sendMessage("§8§M+---------------------------------------+");
                    } else {
                        if (!player.tutorialPassed) {
                            sender.sendMessage("§8§M+---------------------------------------+");
                            sender.sendMessage("   §fHello! I'll be your helper, my name is §bAtomic");
                            sender.sendMessage("   §fI will teach you the basics of the game.");
                            sender.sendMessage("");
                            sender.sendMessage("   §fYou can mine §eWood§f, §8Stone§f and collect §cFood");
                            sender.sendMessage("   §fYou can also get §gGold§f by killing players or using a windmill");
                            sender.sendMessage("   §fThose ressources will help you upgrade and build you base");
                            sender.sendMessage("   §fYou can §aeat §fyour food to gain back §c2 Hearts");
                            sender.sendMessage("   §fBy mining, collecting, killing you will earn §3Exp");
                            sender.sendMessage("   §fIf you collect enough §3Exp§f you will be able to §9Level Up");
                            sender.sendMessage("   §fLeveling up will give you access to new upgrades and tools");
                            sender.sendMessage("   §fIf you got any question, don't be afraid to ask !");
                            sender.sendMessage("");
                            sender.sendMessage("   §4§LWARNING§c: When leaving, if you don't have a Progress Saver");
                            sender.sendMessage("   §cyour progress WILL be lost. You can buy a Progress Saver at the /shop");
                            sender.sendMessage("");
                            sender.sendMessage("   §fWhen you are ready to play please type §b/join §fagain");
                            sender.sendMessage("§8§M+---------------------------------------+");
                            player.tutorialPassed = true;
                        } else {
                            if (!data.saveProgress) {
                                ((Player) sender).setExp(0f);
                                ((Player) sender).setLevel(0);
                                BackpackManager.updateBackpack((Player) sender, "*", "CLEAR", 0);
                            } else {
                                ((Player) sender).setExp((float) player.currentExp / 100);
                                ((Player) sender).setLevel(player.currentAge);
                                player.woodAmount = data.woodAmount;
                                player.stoneAmount = data.stoneAmount;
                                player.foodAmount = data.foodAmount;
                                player.goldAmount = data.goldAmount;
                                player.currentAge = data.currentAge;
                                player.currentExp = data.currentExp;
                                player.requiredExp = data.requiredExp;
                                player.expBar = data.expBar;
                                player.currentFoodType = data.currentFoodType;
                                player.backpackLevel = data.backpackLevel;
                                player.backpack_capacity = data.backpack_capacity;
                                player.backpack_goldRequired = data.backpack_goldRequired;
                                player.backpack_limit_food = data.backpack_limit_food;
                                player.backpack_limit_stone = data.backpack_limit_stone;
                                player.backpack_limit_wood = data.backpack_limit_wood;
                                player.backpack_limit_gold = data.backpack_limit_gold;
                                player.backpack_upgradeCapacity = data.backpack_upgradeCapacity;
                                BackpackManager.updateBackpack((Player) sender, "NONE", "UPDATE", 0);
                            }
                            player.isInGame = true;
                            ((Player) sender).setFoodLevel(19);
                            ((Player) sender).setHealth(20);
                            if (player.currentFoodType.equalsIgnoreCase("CHEESE")) {
                                ItemStack cheese = new ItemStack(Material.GOLDEN_CARROT);
                                ItemMeta cheese_meta = cheese.getItemMeta();
                                cheese_meta.setDisplayName("§c§lFOOD");
                                cheese_meta.setLore(Arrays.asList("\n", "§7Food can be used to regain health"));
                                cheese.setItemMeta(cheese_meta);
                                ((Player) sender).getInventory().setItem(7, cheese);
                            }
                            if (player.currentFoodType.equalsIgnoreCase("COOKIE")) {
                                ItemStack cookie = new ItemStack(Material.COOKIE);
                                ItemMeta cookie_meta = cookie.getItemMeta();
                                cookie_meta.setDisplayName("§c§lFOOD");
                                cookie_meta.setLore(Arrays.asList("\n", "§7Food can be used to regain health"));
                                cookie.setItemMeta(cookie_meta);
                                ((Player) sender).getInventory().setItem(7, cookie);
                            }
                            if (player.currentFoodType.equalsIgnoreCase("APPLE")) {
                                ItemStack apple = new ItemStack(Material.APPLE);
                                ItemMeta apple_meta = apple.getItemMeta();
                                apple_meta.setDisplayName("§c§lFOOD");
                                apple_meta.setLore(Arrays.asList("\n", "§7Food can be used to regain health"));
                                apple.setItemMeta(apple_meta);
                                ((Player) sender).getInventory().setItem(7, apple);
                            }
                            ItemStack item = new ItemStack(Material.WOOD_AXE);
                            ItemMeta item_meta = item.getItemMeta();
                            item_meta.setDisplayName("§a§lMAIN");
                            item_meta.setLore(Arrays.asList("\n", "§7Main can be used to kill/break/farm"));
                            item_meta.setUnbreakable(true);
                            item.setItemMeta(item_meta);
                            ((Player) sender).getInventory().setItem(0, item);

                            SoundManager.playSound((Player) sender, 5);
                            Bukkit.getScheduler().runTask(MooCowIO.instance, () -> ((Player) sender).teleport(server.mapSpawn));
                        }
                    }
                }
            }
        });

        return true;
    }

}
