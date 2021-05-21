package de.kamikazozi.bank.listeners;

import de.kamikazozi.bank.BankSystem;
import de.kamikazozi.bank.utils.Data;
import de.kamikazozi.bank.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author Florian Dohms
 * <p>
 * Copyright (c) 2017 - 2020 by KamiKazozi to present. All rights served.
 */
public class InventoryClickListener implements Listener {

    private Integer value = 0;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals("§3Bank")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bDiamanten einzahlen")) {
                if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND) {
                    Integer amount = player.getInventory().getItemInMainHand().getAmount();
                    if (!(amount <= 0)) {
                        BankSystem.getInstance().getEconomyManager().addDiamonds(player, amount);
                        player.sendMessage(Data.prefix + "§aDu hast §b" + amount + " Diamanten §aeingezahlt");
                        player.getInventory().setItemInMainHand(new ItemBuilder(Material.AIR).build());
                        player.updateInventory();
                        player.closeInventory();
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDu musst einen §bDiamanten §cin die Hand nehmen");
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bDiamanten auszahlen")) {
                Inventory earn = Bukkit.createInventory(null, 9, "§bDiamanten auszahlen");
                earn.addItem(new ItemBuilder(Material.DIAMOND).setName("§bKontostand").setLore("§b" + BankSystem.getInstance().getEconomyManager().getDiamonds(player) + " Diamanten").build());
                earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§d").build());
                earn.addItem(new ItemBuilder(Material.RED_TERRACOTTA).setName("§c-1").build());
                earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§b").build());
                earn.addItem(new ItemBuilder(Material.PLAYER_HEAD).setName("§a0").setSkullOwner("MHF_Question").build());
                earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§a").build());
                earn.addItem(new ItemBuilder(Material.LIME_TERRACOTTA).setName("§a+1").build());
                earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§e").build());
                earn.addItem(new ItemBuilder(Material.DIAMOND).setName("§bAuszahlen").build());
                player.openInventory(earn);

            }else {
                return;
            }
        } else if (event.getView().getTitle().equals("§bDiamanten auszahlen")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§c-1")) {
                if (value == 0) {
                    player.sendMessage(Data.prefix + "§cDu kannst nicht in den Minusbereich gehen");
                } else {
                    value -= 1;
                    Inventory earn = Bukkit.createInventory(null, 9, "§bDiamanten auszahlen");
                    earn.addItem(new ItemBuilder(Material.DIAMOND).setName("§bKontostand").setLore("§b" + BankSystem.getInstance().getEconomyManager().getDiamonds(player) + " Diamanten").build());
                    earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§d").build());
                    earn.addItem(new ItemBuilder(Material.RED_TERRACOTTA).setName("§c-1").build());
                    earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§b").build());
                    earn.addItem(new ItemBuilder(Material.PLAYER_HEAD).setName("§a" + value).setSkullOwner("MHF_Question").build());
                    earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§a").build());
                    earn.addItem(new ItemBuilder(Material.LIME_TERRACOTTA).setName("§a+1").build());
                    earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§e").build());
                    earn.addItem(new ItemBuilder(Material.DIAMOND).setName("§bAuszahlen").build());
                    player.openInventory(earn);
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a+1")) {
                value += 1;
                Inventory earn = Bukkit.createInventory(null, 9, "§bDiamanten auszahlen");
                earn.addItem(new ItemBuilder(Material.DIAMOND).setName("§bKontostand").setLore("§b" + BankSystem.getInstance().getEconomyManager().getDiamonds(player) + " Diamanten").build());
                earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§d").build());
                earn.addItem(new ItemBuilder(Material.RED_TERRACOTTA).setName("§c-1").build());
                earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§b").build());
                earn.addItem(new ItemBuilder(Material.PLAYER_HEAD).setName("§a" + value).setSkullOwner("MHF_Question").build());
                earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§a").build());
                earn.addItem(new ItemBuilder(Material.LIME_TERRACOTTA).setName("§a+1").build());
                earn.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§e").build());
                earn.addItem(new ItemBuilder(Material.EMERALD).setName("§bAuszahlen").build());
                player.openInventory(earn);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bAuszahlen")) {
                if (value > 0) {
                    if(BankSystem.getInstance().getEconomyManager().hasEnoughDiamonds(player, value)) {
                        for(int i = 0; i < value; i++) {
                            player.getInventory().addItem(new ItemBuilder(Material.DIAMOND).build());
                        }
                        BankSystem.getInstance().getEconomyManager().removeDiamonds(player, value);
                        player.sendMessage(Data.prefix + "§aDu hast dir §b" + value + " Diamanten §aausgezahlt");
                        value = 0;
                        player.closeInventory();
                    }else {
                        player.sendMessage(Data.prefix + "§cDu hast dafür nicht genug §bDiamanten");
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDu kannst dir keine §b0 Diamanten §causzahlen lassen");
                    return;
                }
            }else {
                return;
            }
        }
    }
}
