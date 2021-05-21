package de.kamikazozi.bank;/**
 * @author Florian Dohms
 * <p>
 * Copyright (c) 2017 - 2020 by KamiKazozi to present. All rights served.
 */

import de.kamikazozi.bank.commands.CommandBank;
import de.kamikazozi.bank.commands.CommandPay;
import de.kamikazozi.bank.listeners.InventoryClickListener;
import de.kamikazozi.bank.listeners.PlayerInteractAtEntityListener;
import de.kamikazozi.bank.manager.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BankSystem extends JavaPlugin {

    public static BankSystem Instance;

    private EconomyManager economyManager = new EconomyManager();

    @Override
    public void onEnable() {
        Instance = this;

        registerListener();
        registerCommands();
    }

    public void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerInteractAtEntityListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);
    }

    public void registerCommands() {
        getCommand("pay").setExecutor(new CommandPay());
        getCommand("bank").setExecutor(new CommandBank());
    }

    public static BankSystem getInstance() {
        return Instance;
    }

    public EconomyManager getEconomyManager() {
        return economyManager;
    }
}