package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.WalkToBankFast
// import path.to.your.HasUsedShortcut

/**
 * NOTES:
 * Option for teleporting/running back to bank
 */
public class ShouldTakeFastRouteToBank extends BranchTask {

    private WalkToBankFast walktobankfast = new WalkToBankFast();
    private HasUsedShortcut hasusedshortcut = new HasUsedShortcut();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return hasusedshortcut;
    }

    @Override
    public TreeTask successTask() {
        return walktobankfast;
    }
}
