package com.stixx.bots.zmi_runecrafter.player_not_in_bank.ladder_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank.IsUsingFastRoute;

// import path.to.your.IsNearLadder
// import path.to.your.IsUsingFastRoute

/**
 * NOTES:
 * 
 */
public class InLadderArea extends BranchTask {

    private ZMI bot;
    public InLadderArea(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new IsUsingFastRoute(bot);
    }

    @Override
    public TreeTask successTask() {
        return new IsNearLadder(bot);
    }
}
