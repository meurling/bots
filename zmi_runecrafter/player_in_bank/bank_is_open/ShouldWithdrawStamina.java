package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.WithdrawStamina
// import path.to.your.ShouldWithdrawFood

/**
 * NOTES:
 * 
 */
public class ShouldWithdrawStamina extends BranchTask {

    private WithdrawStamina withdrawstamina = new WithdrawStamina();
    private ShouldWithdrawFood shouldwithdrawfood = new ShouldWithdrawFood();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return shouldwithdrawfood;
    }

    @Override
    public TreeTask successTask() {
        return withdrawstamina;
    }
}