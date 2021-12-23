package com.kangbakso.socialcredit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SocialCreditCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("socialcredit")) {
            if (args.length == 0) {
                SocialCreditCommand.showAllCommands(commandSender);
                return true;
            }

            // add
            if (args[0].equalsIgnoreCase("add")) {
                return SocialCreditCommandHandler.handleAdd(commandSender, args);
            }

            // subtract
            if (args[0].equalsIgnoreCase("subtract")) {
                return SocialCreditCommandHandler.handleSubtract(commandSender, args);
            }

            // check
            if (args[0].equalsIgnoreCase("check") && args.length == 1) {
                return SocialCreditCommandHandler.handleCheck(commandSender);
            }

            // checkPlayer
            if (args[0].equalsIgnoreCase("check") && args.length > 1) {
                return SocialCreditCommandHandler.handleCheckPlayer(commandSender, args);
            }

            SocialCreditCommand.showAllCommands(commandSender);
            return true;
        }
        return false;
    }
}
