package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.ShouldStopFollow

/**
 * NOTES:
 * 
 */
public class IsFollowingPlayer extends BranchTask {

    private ZMI bot;
    public IsFollowingPlayer(ZMI bot) {
        this.bot =bot;
    }


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
        return new ShouldStopFollow(bot);
    }
}
