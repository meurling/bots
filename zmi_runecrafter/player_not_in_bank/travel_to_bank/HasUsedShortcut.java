package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.ClickShortcut;

// import path.to.your.WalkToBankSlow
// import path.to.your.ClickShortcut

/**
 * NOTES:
 * Option for teleporting/running back to bank
 */
public class HasUsedShortcut extends BranchTask {

    private WalkToBankSlow walktobankslow = new WalkToBankSlow();
    private ClickShortcut clickshortcut = new ClickShortcut();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return clickshortcut;
    }

    @Override
    public TreeTask successTask() {
        return walktobankslow;
    }
}
