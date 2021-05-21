package de.kamikazozi.bank.commands;/**
 * @author Florian Dohms
 * <p>
 * Copyright (c) 2017 - 2020 by KamiKazozi to present. All rights served.
 */

import de.kamikazozi.bank.BankSystem;
import de.kamikazozi.bank.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPay implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Data.onlyPlayer);
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 2) {
            player.sendMessage(Data.prefix + "§7Benutze: /pay <Spieler> <Betrag>");
            return true;
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Data.noPlayer);
                return true;
            }

            Integer amount = Integer.parseInt(args[0]);
            if (!BankSystem.getInstance().getEconomyManager().hasEnoughDiamonds(player, amount)) {
                player.sendMessage(Data.prefix + "§cDu hast nicht genug §bDiamanten §cauf dem Konto.");
                return true;
            } else {
                BankSystem.getInstance().getEconomyManager().addDiamonds(target, amount);
                target.sendMessage(Data.prefix + "§e" + player.getName() + "§a hat dir §b" + amount + " Diamanten §abezahlt.");
                BankSystem.getInstance().getEconomyManager().removeDiamonds(player, amount);
                player.sendMessage(Data.prefix + "§aDu hast §e" + target.getName() + "§b " + amount + " Diamanten §abezahlt.");
            }
        }
        return true;
    }
}