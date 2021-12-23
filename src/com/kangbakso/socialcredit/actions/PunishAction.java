package com.kangbakso.socialcredit.actions;

import com.kangbakso.socialcredit.Helper;
import com.kangbakso.socialcredit.SocialCredit;
import com.kangbakso.socialcredit.SocialCreditPunishType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class PunishAction {
    private static Server server = Bukkit.getServer();
    private static ConsoleCommandSender commandSource = server.getConsoleSender();
    private static Random random = new Random();

    public static void punish(Player player) {
        String broadcastMessage = ChatColor.RED + player.getDisplayName()
                + ChatColor.RED + " akan dihukum dalam waktu dekat";
        server.broadcastMessage(Helper.broadcastPrefix + broadcastMessage);

        new BukkitRunnable() {
            @Override
            public void run() {
                randomPunish(player);
            }
        }.runTaskLater(SocialCredit.getPlugin(SocialCredit.class), 100L);
    }

    private static void randomPunish(Player player) {
        SocialCreditPunishType punishType = SocialCreditPunishType.randomType();
        Integer intensity = random.nextInt(19) + 1;

        if (punishType == SocialCreditPunishType.BURN) {
            punishBurn(player, intensity);
        } else if (punishType == SocialCreditPunishType.LIGHTNING) {
            punishLightning(player, intensity);
        }
    }

    private static void punishBurn(Player player, Integer intensity) {
        server.dispatchCommand(commandSource, "burn " + player.getName() + " " + intensity);
    }

    private static void punishLightning(Player player, Integer intensity) {
        server.dispatchCommand(commandSource, "lightning " + player.getName() + " " + intensity);
    }
}
