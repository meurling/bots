package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.WithdrawEssence
// import path.to.your.CloseBank

/**
 * NOTES:
 * 
 */
public class ShouldWithdrawEssence extends BranchTask {

    private WithdrawEssence withdrawessence = new WithdrawEssence();
    private CloseBank closebank = new CloseBank();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return closebank;
    }

    @Override
    public TreeTask successTask() {
        return withdrawessence;
    }
}
