package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.WithdrawNPCRunes
// import path.to.your.ShouldWithdrawEssence

/**
 * NOTES:
 * 
 */
public class ShouldWithdrawNPCRunes extends BranchTask {

    private WithdrawNPCRunes withdrawnpcrunes = new WithdrawNPCRunes();
    private ShouldWithdrawEssence shouldwithdrawessence = new ShouldWithdrawEssence();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return shouldwithdrawessence;
    }

    @Override
    public TreeTask successTask() {
        return withdrawnpcrunes;
    }
}
