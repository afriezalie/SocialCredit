package com.kangbakso.socialcredit;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import java.text.NumberFormat;

public class Helper {
    // object
    public static String broadcastPrefix = ChatColor.GOLD + "["
            + ChatColor.DARK_RED + "Pemerintah"
            + ChatColor.GOLD + "]" + " ";

    private static NumberFormat nf = NumberFormat.getNumberInstance();

    public static EntityType[] immoralKillEntityList = {
            EntityType.CAT,
            EntityType.OCELOT,
            EntityType.PLAYER,
            EntityType.WOLF,
    };

    // function
    public static String numberToString(Double number) {
        nf.setMaximumFractionDigits(0);
        return nf.format(number);
    }

    public static boolean isImmoralKill(EntityType entityType) {
        boolean isImmoral = false;
        for (EntityType entity : immoralKillEntityList) {
            if (entityType == entity) {
                isImmoral = true;
                break;
            }
        }
        return isImmoral;
    }
}
