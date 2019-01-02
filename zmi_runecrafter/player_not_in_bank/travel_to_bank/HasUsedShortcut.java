package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.ClickShortcut;

// import path.to.your.WalkToBankSlow
// import path.to.your.ClickShortcut

/**
 * NOTES:
 * Option for teleporting/running back to bank
 */
public class HasUsedShortcut extends BranchTask {

    private ZMI bot;
    public HasUsedShortcut(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new ClickShortcut(bot);
    }

    @Override
    public TreeTask successTask() {
        return  new WalkToBankSlow(bot);
    }
}
