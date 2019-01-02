package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.essence_handling;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.OpenBank;

// import path.to.your.FillPouch
// import path.to.your.OpenBank

/**
 * NOTES:
 * 
 */
public class CanFillPouch extends BranchTask {
    private ZMI bot;
    public CanFillPouch(ZMI bot) {
        this.bot = bot;
    }



    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new OpenBank(bot);
    }

    @Override
    public TreeTask successTask() {
        return new FillPouch(bot);
    }
}
