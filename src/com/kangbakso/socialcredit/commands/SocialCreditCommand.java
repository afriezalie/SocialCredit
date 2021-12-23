package com.kangbakso.socialcredit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Set;

public class SocialCreditCommand {

    private String command;
    private String description;
    private String usage;
    private String permission;

    public static HashMap<String, SocialCreditCommand> commandMap = new HashMap<String, SocialCreditCommand>();

    private SocialCreditCommand(String command, String description, String usage, String permission) {
        this.command = command;
        this.description = description;
        this.usage = usage;
        this.permission = permission;
    }

    public static void initCommand() {
        // add
        commandMap.put("ADD", new SocialCreditCommand(
                "add",
                "Add social credit to a player",
                "/socialcredit add <player> <amount>",
                "socialcredit.admin.add"
        ));

        // subtract
        commandMap.put("SUBTRACT", new SocialCreditCommand(
                "subtract",
                "Subtract social credit from a player",
                "/socialcredit subtract <player> <amount>",
                "socialcredit.admin.subtract"
        ));

        // checkPlayer
        commandMap.put("CHECKPLAYER", new SocialCreditCommand(
                "check",
                "Check a player's social credit",
                "/socialcredit check <player>",
                "socialcredit.command.checkplayer"
        ));

        // check
        commandMap.put("CHECK", new SocialCreditCommand(
                "check",
                "Check social credit",
                "/socialcredit check",
                "socialcredit.command.check"
        ));
    }

    public static void showAllCommands(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.GOLD + "Available commands:");

        Set<String> keys = commandMap.keySet();
        for (String command : keys) {
            SocialCreditCommand selectedCommand = commandMap.get(command);

            if (commandSender.hasPermission(selectedCommand.getPermission())) {
                showUsageMessage(commandSender, command);
            }
        }
    }

    public static void showDetailMessage(CommandSender commandSender, String command) {
        SocialCreditCommand selectedCommand = commandMap.get(command);
        commandSender.sendMessage(ChatColor.GOLD + "Command: " + selectedCommand.getCommand());
        commandSender.sendMessage(ChatColor.GOLD + "Description: " + selectedCommand.getDescription());
        commandSender.sendMessage(ChatColor.GOLD + "Usage:");
        commandSender.sendMessage(ChatColor.GREEN + selectedCommand.getUsage());
    }

    public static void showUsageMessage(CommandSender commandSender, String command) {
        SocialCreditCommand selectedCommand = commandMap.get(command);
        commandSender.sendMessage(ChatColor.GREEN + selectedCommand.getUsage());
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public String getPermission() {
        return permission;
    }
}
