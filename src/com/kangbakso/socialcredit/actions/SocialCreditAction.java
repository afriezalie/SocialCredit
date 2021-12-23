package com.kangbakso.socialcredit.actions;

import com.kangbakso.socialcredit.Helper;
import com.kangbakso.socialcredit.SocialCredit;
import com.kangbakso.socialcredit.events.SocialCreditPunishEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class SocialCreditAction {
    private static Server server = Bukkit.getServer();
    private static Economy economy = SocialCredit.getEconomy();

    public static void add(Player player, Double amount) {
        economy.depositPlayer(player, amount);
        String broadcastMessage = ChatColor.GREEN + player.getDisplayName()
                + ChatColor.GREEN + " +"
                + ChatColor.GREEN + Helper.numberToString(amount)
                + ChatColor.GREEN + " social credit";
        server.broadcastMessage(Helper.broadcastPrefix + broadcastMessage);
    }

    public static void add(Player player, Double amount, Boolean announce) {
        economy.depositPlayer(player, amount);
        if (announce) {
            String broadcastMessage = ChatColor.GREEN + player.getDisplayName()
                    + ChatColor.GREEN + " +"
                    + ChatColor.GREEN + Helper.numberToString(amount)
                    + ChatColor.GREEN + " social credit";
            server.broadcastMessage(Helper.broadcastPrefix + broadcastMessage);
        }
    }

    public static void subtract(Player player, Double amount) {
        economy.withdrawPlayer(player, amount);
        String broadcastMessage = ChatColor.RED + player.getDisplayName()
                + ChatColor.RED + " -"
                + ChatColor.RED + Helper.numberToString(amount)
                + ChatColor.RED + " social credit";
        server.broadcastMessage(Helper.broadcastPrefix + broadcastMessage);

        Double currentBalance = economy.getBalance(player);
        if (currentBalance <= -100d) {
            Bukkit.getPluginManager().callEvent(new SocialCreditPunishEvent(player));
        }
    }

    public static void subtract(Player player, Double amount, Boolean announce) {
        economy.withdrawPlayer(player, amount);
        if (announce) {
            String broadcastMessage = ChatColor.RED + player.getDisplayName()
                    + ChatColor.RED + " -"
                    + ChatColor.RED + Helper.numberToString(amount)
                    + ChatColor.RED + " social credit";
            server.broadcastMessage(Helper.broadcastPrefix + broadcastMessage);
        }

        Double currentBalance = economy.getBalance(player);
        if (currentBalance <= -100d) {
            Bukkit.getPluginManager().callEvent(new SocialCreditPunishEvent(player));
        }
    }

    public static Double check(OfflinePlayer player) {
        return economy.getBalance(player);
    }

    public static void reset(Player player) {
        Double currentBalance = economy.getBalance(player);
        if (currentBalance < 0) {
            economy.depositPlayer(player, Math.abs(currentBalance));
        } else {
            economy.withdrawPlayer(player, Math.abs(currentBalance));
        }
    }
}
