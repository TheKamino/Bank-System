package de.kamikazozi.bank.commands;/**
 * @author Florian Dohms
 * <p>
 * Copyright (c) 2017 - 2020 by KamiKazozi to present. All rights served.
 */

import de.kamikazozi.bank.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandBank implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Data.onlyPlayer);
            return true;
        }

        if (sender.hasPermission("bank.create") || sender.hasPermission("bank.*")) {
            Player player = (Player) sender;
            if(args.length == 0) {
                Villager villager = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
                villager.setCustomName("§3Bank");
                villager.setCustomNameVisible(true);
                villager.setVillagerType(Villager.Type.SWAMP);
                villager.setNoDamageTicks(999*999*999);
                villager.setCollidable(false);
                villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999*999*999, 30));
            }
        } else {
            sender.sendMessage(Data.noPerm);
        }
        return true;
    }
}