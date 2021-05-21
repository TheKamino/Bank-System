package de.kamikazozi.bank.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * @author Florian Dohms
 * <p>
 * Copyright (c) 2017 - 2020 by KamiKazozi to present. All rights served.
 */
public class EconomyManager {

    File file = new File("plugins/BankSystem", "economy.yml");
    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void setDiamonds(Player target, Integer amount) {
        cfg.set("Diamonds" + "." + target.getName(), amount);
        saveFile();
    }

    public void addDiamonds(Player target, Integer amount) {
        cfg.set("Diamonds" + "." + target.getName(), getDiamonds(target) + amount);
        saveFile();
    }

    public void removeDiamonds(Player target, Integer amount) {
        cfg.set("Diamonds" + "." + target.getName(), getDiamonds(target) - amount);
        saveFile();
    }

    public Integer getDiamonds(Player target) {
        return cfg.getInt("Diamonds" + "." + target.getName());
    }

    public boolean hasEnoughDiamonds(Player target, Integer amount) {
        if (getDiamonds(target) >= amount) {
            return true;
        } else {
            return false;
        }
    }

    private void saveFile() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
