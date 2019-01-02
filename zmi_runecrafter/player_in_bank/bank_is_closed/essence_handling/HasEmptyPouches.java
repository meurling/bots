package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.essence_handling;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.IsInventoryFull;

// import path.to.your.CanFillPouch
// import path.to.your.IsInventoryFull

/**
 * NOTES:
 * 
 */
public class HasEmptyPouches extends BranchTask {

    private ZMI bot;
    public HasEmptyPouches(ZMI bot) {
        this.bot = bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new IsInventoryFull(bot);
    }

    @Override
    public TreeTask successTask() {
        return new CanFillPouch(bot);
    }
}
