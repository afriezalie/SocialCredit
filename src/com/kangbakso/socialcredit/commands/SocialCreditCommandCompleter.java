package com.kangbakso.socialcredit.commands;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.kangbakso.socialcredit.commands.SocialCreditCommand.commandMap;

public class SocialCreditCommandCompleter implements TabCompleter {

    private static Server server = Bukkit.getServer();
    private final List<String> EMPTY_AUTO_COMPLETE;

    public SocialCreditCommandCompleter() {
        EMPTY_AUTO_COMPLETE = new ArrayList<String>();
        EMPTY_AUTO_COMPLETE.add("");
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("socialcredit")) {

            // command type
            if (args.length == 1) {
                List<String> autocomplete = new ArrayList<String>();
                Set<String> keys = commandMap.keySet();
                for (String key : keys) {
                    SocialCreditCommand selectedCommand = commandMap.get(key);
                    if (commandSender.hasPermission(selectedCommand.getPermission())) {
                        autocomplete.add(selectedCommand.getCommand());
                    }
                }
                return autocomplete;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("check")) {
               if (!commandSender.hasPermission(commandMap.get("CHECKPLAYER").getPermission())) {
                   return EMPTY_AUTO_COMPLETE;
               }
            }

            // player
            if (args.length == 2) {
                List<String> autocomplete = new ArrayList<String>();
                Player[] onlinePlayers = server.getOnlinePlayers().toArray(Player[]::new);
                for (Player player : onlinePlayers) {
                    autocomplete.add(player.getName());
                }
                return autocomplete;
            }
            return EMPTY_AUTO_COMPLETE;
        }
        return null;
    }
}
