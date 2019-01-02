package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.WalkToBankFast
// import path.to.your.HasUsedShortcut

/**
 * NOTES:
 * Option for teleporting/running back to bank
 */
public class ShouldTakeFastRouteToBank extends BranchTask {

    private ZMI bot;
    public ShouldTakeFastRouteToBank(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new HasUsedShortcut(bot);
    }

    @Override
    public TreeTask successTask() {
        return new WalkToBankFast(bot);
    }
}
