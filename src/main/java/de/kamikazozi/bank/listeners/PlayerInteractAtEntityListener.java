package de.kamikazozi.bank.listeners;

import de.kamikazozi.bank.BankSystem;
import de.kamikazozi.bank.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;

/**
 * @author Florian Dohms
 * <p>
 * Copyright (c) 2017 - 2020 by KamiKazozi to present. All rights served.
 */
public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.VILLAGER) return;

        Villager villager = (Villager) event.getRightClicked();

        if (!villager.getCustomName().equals("§3Bank")) return;

        Inventory select = Bukkit.createInventory(null, InventoryType.HOPPER, "§3Bank");
        select.addItem(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_ArrowDown").setName("§bDiamanten einzahlen").build());
        select.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§b").build());
        select.addItem(new ItemBuilder(Material.DIAMOND).setName("§bKontostand").setLore("§b" + BankSystem.getInstance().getEconomyManager().getDiamonds(event.getPlayer()) + " Diamanten").build());
        select.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§a").build());
        select.addItem(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_ArrowUp").setName("§bDiamanten auszahlen").build());
        event.getPlayer().openInventory(select);
    }
}
