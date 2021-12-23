package com.kangbakso.socialcredit.commands;

import com.kangbakso.socialcredit.Helper;
import com.kangbakso.socialcredit.actions.SocialCreditAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static com.kangbakso.socialcredit.commands.SocialCreditCommand.commandMap;

public class SocialCreditCommandHandler {

    private static Server server = Bukkit.getServer();

    // add
    public static boolean handleAdd(CommandSender commandSender, String[] args) {
        final String COMMAND_KEY = "ADD";
        SocialCreditCommand selectedCommand = commandMap.get(COMMAND_KEY);

        if (!commandSender.hasPermission(selectedCommand.getPermission())) {
            onPermissionError(commandSender);
            return true;
        }

        if (args.length < 3) {
            SocialCreditCommand.showDetailMessage(commandSender, COMMAND_KEY);
            return true;
        }

        Player player = server.getPlayer(args[1]);
        if (player == null) {
            commandSender.sendMessage(ChatColor.DARK_RED + "Error: Player not found");
            return true;
        }

        try {
            Double amount = Double.parseDouble(args[2]);
            SocialCreditAction.add(player, amount);
        } catch (NumberFormatException e) {
            SocialCreditCommand.showDetailMessage(commandSender, COMMAND_KEY);
        }
        return true;
    }

    // subtract
    public static boolean handleSubtract(CommandSender commandSender, String[] args) {
        final String COMMAND_KEY = "SUBTRACT";
        SocialCreditCommand selectedCommand = commandMap.get(COMMAND_KEY);

        if (!commandSender.hasPermission(selectedCommand.getPermission())) {
            onPermissionError(commandSender);
            return true;
        }

        if (args.length < 3) {
            SocialCreditCommand.showDetailMessage(commandSender, COMMAND_KEY);
            return true;
        }

        Player player = server.getPlayer(args[1]);
        if (player == null) {
            commandSender.sendMessage(ChatColor.DARK_RED + "Error: Player not found");
            return true;
        }

        try {
            Double amount = Double.parseDouble(args[2]);
            SocialCreditAction.subtract(player, amount);
        } catch (NumberFormatException e) {
            SocialCreditCommand.showDetailMessage(commandSender, COMMAND_KEY);
        }
        return true;
    }

    // check
    public static boolean handleCheck(CommandSender commandSender) {
        final String COMMAND_KEY = "CHECK";
        SocialCreditCommand selectedCommand = commandMap.get(COMMAND_KEY);

        if (commandSender instanceof ConsoleCommandSender) {
            SocialCreditCommand.showDetailMessage(commandSender, "CHECKPLAYER");
            return true;
        }

        if (!commandSender.hasPermission(selectedCommand.getPermission())) {
            onPermissionError(commandSender);
            return true;
        }

        Player player = (Player) commandSender;
        Double credit = SocialCreditAction.check(player);
        String message = ChatColor.GREEN + "Social credit saat ini: "
                + ChatColor.RED + Helper.numberToString(credit);
        commandSender.sendMessage(message);
        return true;
    }

    // checkPlayer
    public static boolean handleCheckPlayer(CommandSender commandSender, String[] args) {
        final String COMMAND_KEY = "CHECKPLAYER";
        SocialCreditCommand selectedCommand = commandMap.get(COMMAND_KEY);

        if (!commandSender.hasPermission(selectedCommand.getPermission())) {
            onPermissionError(commandSender);
            return true;
        }

        OfflinePlayer offlinePlayer = server.getOfflinePlayer(args[1]);
        if (offlinePlayer == null) {
            commandSender.sendMessage(ChatColor.DARK_RED + "Error: Player not found");
            return true;
        }
        Double credit = SocialCreditAction.check(offlinePlayer);
        String message = ChatColor.GREEN + "Social credit "
                + offlinePlayer.getName()
                + ChatColor.GREEN + " saat ini: "
                + ChatColor.RED + Helper.numberToString(credit);
        commandSender.sendMessage(message);
        return true;
    }

    private static void onPermissionError(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Anda berusaha mengakses rahasia negara");
        SocialCreditAction.subtract((Player) commandSender, 99d);
    }
}
