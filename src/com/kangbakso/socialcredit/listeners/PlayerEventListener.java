package com.kangbakso.socialcredit.listeners;

import com.kangbakso.socialcredit.Helper;
import com.kangbakso.socialcredit.actions.PunishAction;
import com.kangbakso.socialcredit.actions.SocialCreditAction;
import com.kangbakso.socialcredit.events.SocialCreditPunishEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.sendMessage(ChatColor.RED + "Kamu tidak diperkerjakan untuk mati!");
        SocialCreditAction.subtract(player, 10d);
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Player killer = entity.getKiller();
        EntityType entityType = event.getEntityType();

        if (killer == null) return;

        if (entity instanceof Monster) {
            killer.sendMessage(ChatColor.GREEN + "Terima kasih telah membunuh monster. +2 social credit");
            SocialCreditAction.add(killer, 2d, false);
            return;
        }

        if (Helper.isImmoralKill(entityType)) {
            killer.sendMessage(ChatColor.RED + "Kamu adalah pembunuh yang kejam!");
            SocialCreditAction.subtract(killer, 90d);
        }
    }

    @EventHandler
    public void onPlayerPunished(SocialCreditPunishEvent event) {
        Player player = event.getPlayer();
        SocialCreditAction.reset(player);
        PunishAction.punish(player);
    }
}
