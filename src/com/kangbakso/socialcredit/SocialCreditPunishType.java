package com.kangbakso.socialcredit;

import java.util.Random;

public enum SocialCreditPunishType {
    BURN, LIGHTNING;

    private static final SocialCreditPunishType[] VALUES = values();
    private static final Integer SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static SocialCreditPunishType randomType() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
