package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.ShouldStopFollow

/**
 * NOTES:
 * 
 */
public class IsFollowingPlayer extends BranchTask {

    private ShouldStopFollow shouldstopfollow = new ShouldStopFollow();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return null;
    }

    @Override
    public TreeTask successTask() {
        return shouldstopfollow;
    }
}
