package com.stixx.bots.zmi_runecrafter.player_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_in_bank.MustEatFood;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches.IsContactingDarkMage;

// import path.to.your.IsContactingDarkMage
// import path.to.your.MustEatFood

/**
 * NOTES:
 * 
 */
public class MustRepairPouch extends BranchTask {

    private IsContactingDarkMage iscontactingdarkmage = new IsContactingDarkMage();
    private MustEatFood musteatfood = new MustEatFood();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return musteatfood;
    }

    @Override
    public TreeTask successTask() {
        return iscontactingdarkmage;
    }
}
