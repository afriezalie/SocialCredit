package com.kangbakso.socialcredit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SocialCreditPunishEvent extends Event {
    private final Player player;
    private static final HandlerList HANDLERS = new HandlerList();

    public SocialCreditPunishEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
