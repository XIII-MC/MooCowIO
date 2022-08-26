package com.xiii.moocowio.game.displays;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.PlayerData;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.core.utils.InventoryUtils;
import com.xiii.moocowio.game.manager.BackpackManager;
import com.xiii.moocowio.game.manager.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BackpackDisplay implements Listener {

    public static void backpackGUI(Player p, int gui) {
        Bukkit.getScheduler().runTask(MooCowIO.instance, () -> {

            p.closeInventory();

            // MAIN GUI
            if(gui == 0) {

                SoundManager.playSound(p, 8);

                Inventory main_gui = Bukkit.createInventory(p.getPlayer(), 27, "            §6§L§nBACKPACK");
                ItemStack glass = (ItemUtils.customItem(Material.STAINED_GLASS_PANE, " ", " "));

                ItemStack deposit = (ItemUtils.customItem(Material.DISPENSER, "§a§nDeposit", " ", "§fDeposit the resources from your inventory to", "§fyour backpack. They will be", "§fwithdrawable on the withdraw interface."));
                ItemStack withdraw = (ItemUtils.customItem(Material.DROPPER, "§c§nWithdraw", " ", "§fWithdraw resources from your backpack to your", "§finventory. Those resources will be depositable", "§fand dropable when being in your inventory.", "§fYou can also place them or use them"));
                ItemStack close = (ItemUtils.customItem(Material.BARRIER, "§4§nClose Menu", " ", "§fClose menu and go back to the game."));

                for (int i = 0; i < 27; i++) main_gui.setItem(i, glass);

                main_gui.setItem(11, deposit);
                main_gui.setItem(13, close);
                main_gui.setItem(15, withdraw);

                p.getPlayer().openInventory(main_gui);

            }

            // DEPOSIT GUI
            if(gui == 1) {

                SoundManager.playSound(p, 8);

                Inventory gui_deposit = Bukkit.createInventory(p.getPlayer(), 27, "              §2§l§nDEPOSIT");
                ItemStack glass = (ItemUtils.customItem(Material.STAINED_GLASS_PANE, " ", " "));

                ItemStack wood = (ItemUtils.customItem(Material.LOG, "§a§nDeposit§r§a: §e§nWood", " ", "§e§l§nLeft click§f to deposit §n1§r §8| §6§l§nRight click§f to deposit §nall", "\n", "§fDeposit §e§nwood§f from your inventory to", "§fyour backpack. The §e§nwood§f will be", "§fwithdrawable on the withdraw interface."));
                ItemStack stone = (ItemUtils.customItem(Material.COBBLESTONE, "§a§nDeposit§r§a: §7Stone", " ", "§e§l§nLeft click§f to deposit §n1§r §8| §6§l§nRight click§f to deposit §nall", "\n", "§fDeposit §7§nstone§f from your inventory to", "§fyour backpack. This §7§nstone§f will be", "§fwithdrawable on the withdraw interface."));
                ItemStack goBack = (ItemUtils.customItem(Material.ARROW, "§4§nGo Back", " ", "§fGo back to the main Backpack menu."));
                ItemStack gold = (ItemUtils.customItem(Material.GOLD_INGOT, "§a§nDeposit§r§a: §6§nGold", " ", "§e§l§nLeft click§f to deposit §n1§r §8| §6§l§nRight click§f to deposit §nall", "\n", "§fDeposit §6§ngold§f from your inventory to", "§fyour backpack. The §6§ngold§f will be", "§fwithdrawable on the withdraw interface."));

                for (int i = 0; i < 27; i++) gui_deposit.setItem(i, glass);

                gui_deposit.setItem(11, wood);
                gui_deposit.setItem(13, stone);
                gui_deposit.setItem(26, goBack);
                gui_deposit.setItem(15, gold);

                p.getPlayer().closeInventory();
                p.getPlayer().openInventory(gui_deposit);

            }

            // WITHDRAW GUI
            if(gui == 2) {

                SoundManager.playSound(p, 8);
                TempData data = Data.data.getTempData(p);

                Inventory gui_deposit = Bukkit.createInventory(p.getPlayer(), 27, "             §c§lWITHDRAW");
                ItemStack glass = (ItemUtils.customItem(Material.STAINED_GLASS_PANE, " ", " "));

                ItemStack wood = (ItemUtils.customItem(Material.LOG, "§c§nWithdraw§r§a: §e§nWood", " ", "§e§l§nLeft click§f to withdraw §n1§r §8| §6§l§nRight click§f to withdraw §nall", "\n", "§fWithdraw §e§nwood§f from your backpack to", "§fto your inventory. The §e§nwood§f will be", "§fdepositable on the the deposit interface."));
                ItemStack stone = (ItemUtils.customItem(Material.COBBLESTONE, "§c§nWithdraw§r§a: §7Stone", " ", "§e§l§nLeft click§f to withdraw §n1§r §8| §6§l§nRight click§f to withdraw §nall", "\n", "§fWithdraw §7§nstone§f from your backpack to", "§fto your inventory. This §7§nstone§f will be", "§fdepositable on the the deposit interface."));
                ItemStack goBack = (ItemUtils.customItem(Material.ARROW, "§4§nGo Back", " ", "§fGo back to the main Backpack menu."));
                ItemStack gold = (ItemUtils.customItem(Material.GOLD_INGOT, "§c§nWithdraw§r§a: §6§nGold", " ", "§e§l§nLeft click§f to withdraw §n1§r §8| §6§l§nRight click§f to withdraw §nall", "\n", "§fWithdraw §6§ngold§f from your backpack to", "§fto your inventory. The §6§ngold§f will be", "§fdepositable on the the deposit interface."));

                for (int i = 0; i < 27; i++) gui_deposit.setItem(i, glass);

                gui_deposit.setItem(11, wood);
                gui_deposit.setItem(13, stone);
                gui_deposit.setItem(26, goBack);
                gui_deposit.setItem(15, gold);

                p.getPlayer().closeInventory();
                p.getPlayer().openInventory(gui_deposit);

            }

        });

    }

    @EventHandler
    public void onBpGUIClick(InventoryClickEvent e) {
        if(e.getView().getTitle().contains("BACKPACK") || e.getView().getTitle().contains("DEPOSIT") || e.getView().getTitle().contains("WITHDRAW")) {
            e.setCancelled(true);
            Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
                TempData player = Data.data.getTempData((Player) e.getWhoClicked());
                PlayerData data = Data.data.getUserData((Player) e.getWhoClicked());

                if(e.getView().getTitle().contains("BACKPACK")) {

                    if (e.getRawSlot() == 11) backpackGUI((Player) e.getWhoClicked(), 1);
                    if (e.getRawSlot() == 13) e.getWhoClicked().closeInventory();
                    if (e.getRawSlot() == 15) backpackGUI((Player) e.getWhoClicked(), 2);

                }

                if(e.getView().getTitle().contains("DEPOSIT")) {
                    int overStoneAmount = 0;
                    int overWoodAmount = 0;
                    int overGoldAmount = 0;
                    if (e.getRawSlot() == 26) backpackGUI((Player) e.getWhoClicked(), 0);
                    if (e.getRawSlot() == 13) {
                        if (e.isLeftClick()) {
                            if(InventoryUtils.hasItem((Player) e.getWhoClicked(), new  ItemStack(Material.COBBLESTONE))) {
                                if (player.backpack_limit_stone - (player.stoneAmount + 1) >= 0) {
                                    InventoryUtils.removeOne(e.getWhoClicked().getInventory(), new ItemStack(Material.COBBLESTONE));
                                    player.stoneAmount += 1;
                                    e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §71 Stone");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Stone)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Stone)");
                        } else if (e.isRightClick()) {
                            if(InventoryUtils.hasItem((Player) e.getWhoClicked(), new  ItemStack(Material.COBBLESTONE))) {
                                int invStoneAmount = InventoryUtils.getAmount((Player) e.getWhoClicked(), new ItemStack(Material.COBBLESTONE));
                                if (player.backpack_limit_stone - (player.stoneAmount + invStoneAmount) < 0) {
                                    overStoneAmount = (player.backpack_limit_stone - (player.stoneAmount + invStoneAmount));
                                    invStoneAmount -= overStoneAmount;
                                }
                                if (player.backpack_limit_stone - (player.stoneAmount + invStoneAmount) >= 0) {
                                    e.getWhoClicked().getInventory().remove(Material.COBBLESTONE);
                                    player.stoneAmount += invStoneAmount;
                                    if (overStoneAmount > 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §7" + invStoneAmount + " Stone (" + overStoneAmount + " Stone couldn't be deposited)");
                                    if (overStoneAmount <= 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §7" + invStoneAmount + " Stone");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Stone)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Stone)");
                        }
                    }

                    if (e.getRawSlot() == 11) {
                        if (e.isLeftClick()) {
                            if(InventoryUtils.hasItem((Player) e.getWhoClicked(), new  ItemStack(Material.LOG))) {
                                if (player.backpack_limit_wood - (player.woodAmount + 1) >= 0) {
                                    InventoryUtils.removeOne(e.getWhoClicked().getInventory(), new ItemStack(Material.LOG));
                                    player.woodAmount += 1;
                                    e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §e1 Wood");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Wood)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Wood)");
                        } else if (e.isRightClick()) {
                            if(InventoryUtils.hasItem((Player) e.getWhoClicked(), new  ItemStack(Material.LOG))) {
                                int invWoodAmount = InventoryUtils.getAmount((Player) e.getWhoClicked(), new ItemStack(Material.LOG));
                                if (player.backpack_limit_wood - (player.woodAmount + invWoodAmount) < 0) {
                                    overWoodAmount = (player.backpack_limit_wood - (player.woodAmount + invWoodAmount));
                                    invWoodAmount -= overWoodAmount;
                                }
                                if (player.backpack_limit_wood - (player.woodAmount + invWoodAmount) >= 0) {
                                    e.getWhoClicked().getInventory().remove(Material.LOG);
                                    player.woodAmount += invWoodAmount;
                                    if (overWoodAmount > 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §e" + invWoodAmount + " Wood §7(" + overWoodAmount + " Wood couldn't be deposited)");
                                    if (overWoodAmount <= 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §e" + invWoodAmount + " Wood");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §4§lERROR! §cPlease report this to Admins. §7(c002)§c ISA=" + invWoodAmount + " OSA=" + overWoodAmount + " SA=" + player.woodAmount + " B_LS=" + player.backpack_limit_wood);
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Wood)");
                        }
                    }

                    if (e.getRawSlot() == 15) {
                        if (e.isLeftClick()) {
                            if(InventoryUtils.hasItem((Player) e.getWhoClicked(), new  ItemStack(Material.GOLD_INGOT))) {
                                if (player.backpack_limit_gold - (player.goldAmount + 1) >= 0) {
                                    InventoryUtils.removeOne(e.getWhoClicked().getInventory(), new ItemStack(Material.GOLD_INGOT));
                                    player.goldAmount += 1;
                                    e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §61 Gold");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Gold)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Gold)");
                        } else if (e.isRightClick()) {
                            if(InventoryUtils.hasItem((Player) e.getWhoClicked(), new  ItemStack(Material.GOLD_INGOT))) {
                                int invGoldAmount = InventoryUtils.getAmount((Player) e.getWhoClicked(), new ItemStack(Material.GOLD_INGOT));
                                if (player.backpack_limit_gold - (player.goldAmount + invGoldAmount) < 0) {
                                    overGoldAmount = (player.backpack_limit_gold - (player.goldAmount + invGoldAmount));
                                    invGoldAmount -= overGoldAmount;
                                }
                                if (player.backpack_limit_gold - (player.goldAmount + invGoldAmount) >= 0) {
                                    e.getWhoClicked().getInventory().remove(Material.GOLD_INGOT);
                                    player.goldAmount += invGoldAmount;
                                    if (overGoldAmount > 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §6" + invGoldAmount + " Gold §7(" + overGoldAmount + " Gold couldn't be deposited)");
                                    if (overGoldAmount <= 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aDeposit success! You deposited §6" + invGoldAmount + " Gold");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §4§lERROR! §cPlease report this to Admins. §7(c002)§c ISA=" + invGoldAmount + " OSA=" + overGoldAmount + " SA=" + player.goldAmount + " B_LS=" + player.backpack_limit_gold);
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Gold)");
                        }
                    }
                }

                if(e.getView().getTitle().contains("WITHDRAW")) {

                    if(e.getRawSlot() == 26) backpackGUI((Player) e.getWhoClicked(), 0);

                    if (e.getRawSlot() == 13) {
                        if (e.isLeftClick()) {
                            if(player.stoneAmount > 0) {
                                if (player.stoneAmount - 1 >= 0) {
                                    boolean canFit = false;
                                    int amountCanFit = 0;
                                    if(InventoryUtils.canStore((Player) e.getWhoClicked(), new ItemStack(Material.COBBLESTONE))) {
                                        if(player.stoneAmount - 1 >= 0) {
                                            canFit = true;
                                            amountCanFit = 1;
                                        }
                                    }
                                    if(canFit && amountCanFit > 0) {
                                        e.getWhoClicked().getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
                                        player.stoneAmount -= 1;
                                        e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §71 Stone");
                                        BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                    } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough inventory space! §7(Stone)");
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Stone)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Stone)");
                        } else if (e.isRightClick()) {
                            if(player.stoneAmount > 0) {
                                boolean canFit = false;
                                int amountCanFit = 0;
                                if(InventoryUtils.canStore((Player) e.getWhoClicked(), new ItemStack(Material.COBBLESTONE, player.stoneAmount))) {
                                    canFit = true;
                                    amountCanFit = player.stoneAmount;
                                }
                                if (canFit && amountCanFit > 0) {
                                    e.getWhoClicked().getInventory().addItem(new ItemStack(Material.COBBLESTONE, amountCanFit));
                                    player.stoneAmount -= amountCanFit;
                                    if (amountCanFit <= 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §7" + amountCanFit + " Stone");
                                    if (amountCanFit > 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §7" + amountCanFit + " Stone");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough inventory space! §è7(Stone)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Stone)");
                        }
                    }

                    if (e.getRawSlot() == 11) {
                        if (e.isLeftClick()) {
                            if(player.woodAmount > 0) {
                                if (player.woodAmount - 1 >= 0) {
                                    boolean canFit = false;
                                    int amountCanFit = 0;
                                    if(InventoryUtils.canStore((Player) e.getWhoClicked(), new ItemStack(Material.LOG))) {
                                        if(player.woodAmount - 1 >= 0) {
                                            canFit = true;
                                            amountCanFit = 1;
                                        }
                                    }
                                    if(canFit && amountCanFit > 0) {
                                        e.getWhoClicked().getInventory().addItem(new ItemStack(Material.LOG, 1));
                                        player.woodAmount -= 1;
                                        e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §e1 Wood");
                                        BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                    } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough inventory space! §7(Wood)");
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Wood)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Wood)");
                        } else if (e.isRightClick()) {
                            if(player.woodAmount > 0) {
                                boolean canFit = false;
                                int amountCanFit = 0;
                                if(InventoryUtils.canStore((Player) e.getWhoClicked(), new ItemStack(Material.LOG, player.woodAmount))) {
                                    canFit = true;
                                    amountCanFit = player.woodAmount;
                                }
                                if (canFit && amountCanFit > 0) {
                                    e.getWhoClicked().getInventory().addItem(new ItemStack(Material.LOG, amountCanFit));
                                    player.woodAmount -= amountCanFit;
                                    if (amountCanFit <= 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §e" + amountCanFit + " Wood");
                                    if (amountCanFit > 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §e" + amountCanFit + " Wood");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough inventory space! §è7(Wood)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Wood)");
                        }
                    }

                    if (e.getRawSlot() == 15) {
                        if (e.isLeftClick()) {
                            if(player.goldAmount > 0) {
                                if (player.goldAmount - 1 >= 0) {
                                    boolean canFit = false;
                                    int amountCanFit = 0;
                                    if(InventoryUtils.canStore((Player) e.getWhoClicked(), new ItemStack(Material.GOLD_INGOT))) {
                                        if(player.goldAmount - 1 >= 0) {
                                            canFit = true;
                                            amountCanFit = 1;
                                        }
                                    }
                                    if(canFit && amountCanFit > 0) {
                                        e.getWhoClicked().getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
                                        player.goldAmount -= 1;
                                        e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §51 Gold");
                                        BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                    } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough inventory space! §7(Gold)");
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Gold)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Gold)");
                        } else if (e.isRightClick()) {
                            if(player.goldAmount > 0) {
                                boolean canFit = false;
                                int amountCanFit = 0;
                                if(InventoryUtils.canStore((Player) e.getWhoClicked(), new ItemStack(Material.GOLD_INGOT, player.goldAmount))) {
                                    canFit = true;
                                    amountCanFit = player.goldAmount;
                                }
                                if (canFit && amountCanFit > 0) {
                                    e.getWhoClicked().getInventory().addItem(new ItemStack(Material.GOLD_INGOT, amountCanFit));
                                    player.goldAmount -= amountCanFit;
                                    if (amountCanFit <= 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §5" + amountCanFit + " Gold");
                                    if (amountCanFit > 0) e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aWithdraw success! You withdrawed §5" + amountCanFit + " Gold");
                                    BackpackManager.updateBackpack((Player) e.getWhoClicked(), "NONE", "UPDATE", 0);
                                } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough inventory space! §è7(Gold)");
                            } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Gold)");
                        }
                    }
                }
            });
        }
    }

}
