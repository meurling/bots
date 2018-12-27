package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.essence_handling;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.OpenBank;

// import path.to.your.FillPouch
// import path.to.your.OpenBank

/**
 * NOTES:
 * 
 */
public class CanFillPouch extends BranchTask {

    private FillPouch fillpouch = new FillPouch();
    private OpenBank openbank = new OpenBank();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return openbank;
    }

    @Override
    public TreeTask successTask() {
        return fillpouch;
    }
}
