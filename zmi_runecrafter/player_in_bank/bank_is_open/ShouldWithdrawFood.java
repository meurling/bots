package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.WithdrawFood
// import path.to.your.ShouldWithdrawNPCRunes

/**
 * NOTES:
 * 
 */
public class ShouldWithdrawFood extends BranchTask {

    private WithdrawFood withdrawfood = new WithdrawFood();
    private ShouldWithdrawNPCRunes shouldwithdrawnpcrunes = new ShouldWithdrawNPCRunes();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return shouldwithdrawnpcrunes;
    }

    @Override
    public TreeTask successTask() {
        return withdrawfood;
    }
}
