package com.stixx.bots.zmi_runecrafter.player_not_in_bank.ladder_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.ClickLadder
// import path.to.your.TravelToLadder

/**
 * NOTES:
 * 
 */
public class IsNearLadder extends BranchTask {

    private ClickLadder clickladder = new ClickLadder();
    private TravelToLadder traveltoladder = new TravelToLadder();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return traveltoladder;
    }

    @Override
    public TreeTask successTask() {
        return clickladder;
    }
}
