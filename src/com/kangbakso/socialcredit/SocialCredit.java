package com.kangbakso.socialcredit;

import com.earth2me.essentials.Essentials;
import com.kangbakso.socialcredit.commands.SocialCreditCommand;
import com.kangbakso.socialcredit.commands.SocialCreditCommandCompleter;
import com.kangbakso.socialcredit.commands.SocialCreditCommandExecutor;
import com.kangbakso.socialcredit.listeners.PlayerEventListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class SocialCredit extends JavaPlugin {

    private static Server server = null;
    private static Economy economy = null;
    private static Essentials essentials = null;

    @Override
    public void onEnable() {
        server = getServer();

        if (!setupEconomy()) {
            server.getConsoleSender().sendMessage(ChatColor.RED + "[SocialCredit] please install VaultAPI");
            server.getPluginManager().disablePlugin(this);
            return;
        }

        if (!setupEssentials()) {
            server.getConsoleSender().sendMessage(ChatColor.RED + "[SocialCredit] please install EssentialsX");
            server.getPluginManager().disablePlugin(this);
            return;
        }

        server.getPluginManager().registerEvents(new PlayerEventListener(), this);
        setupCommand();

        server.getConsoleSender().sendMessage(ChatColor.GREEN + "[SocialCredit] has been enabled");
    }

    @Override
    public void onDisable() {
        server.getConsoleSender().sendMessage(ChatColor.RED + "[SocialCredit] has been disabled");
    }

    private void setupCommand() {
        SocialCreditCommand.initCommand();
        SocialCreditCommandExecutor socialCreditCommandExecutor = new SocialCreditCommandExecutor();
        SocialCreditCommandCompleter socialCreditCommandCompleter = new SocialCreditCommandCompleter();
        getCommand("socialcredit").setExecutor(socialCreditCommandExecutor);
        getCommand("socialcredit").setTabCompleter(socialCreditCommandCompleter);
    }

    private boolean setupEssentials() {
        Plugin plugin = server.getPluginManager().getPlugin("Essentials");
        if (plugin == null) {
            return false;
        }
        essentials = (Essentials) plugin;
        return true;
    }

    private boolean setupEconomy() {
        if (server.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = server.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static Essentials getEssentials() {
        return essentials;
    }
}
