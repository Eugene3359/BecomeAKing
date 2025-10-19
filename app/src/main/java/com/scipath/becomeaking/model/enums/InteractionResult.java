package com.scipath.becomeaking.model.enums;

import com.scipath.becomeaking.R;

public enum InteractionResult {

    Successful(0),
    NotEnoughMoney(R.string.not_enough_money),
    NotEnoughStrength(R.string.not_enough_strength_points),
    NoTimeLeft(R.string.you_dont_have_a_time),
    NotEnoughReputation(R.string.not_enough_reputation),
    HorseAndWeaponRequired(R.string.horse_and_weapon_required),
    NullPersonage(R.string.null_personage_error);


    private final int messageId;


    InteractionResult(int messageId) {
        this.messageId = messageId;
    }


    public int getMessageId() {
        return messageId;
    }
}
