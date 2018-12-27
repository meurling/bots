package com.stixx.bots.zmi_runecrafter.player_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.OpenBank;

// import path.to.your.OpenBank
// import path.to.your.MustRepairPouch

/**
 * NOTES:
 * 
 */
public class MustDepositRunes extends BranchTask {

    private OpenBank openbank = new OpenBank();
    private MustRepairPouch mustrepairpouch = new MustRepairPouch();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return mustrepairpouch;
    }

    @Override
    public TreeTask successTask() {
        return openbank;
    }
}
