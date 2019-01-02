package com.stixx.bots.zmi_runecrafter.player_not_in_bank.ladder_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.ClickLadder
// import path.to.your.TravelToLadder

/**
 * NOTES:
 * 
 */
public class IsNearLadder extends BranchTask {

    private ZMI bot;
    public IsNearLadder(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new TravelToLadder(bot);
    }

    @Override
    public TreeTask successTask() {
        return new ClickLadder(bot);
    }
}
