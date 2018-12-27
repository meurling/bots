package com.stixx.bots.zmi_runecrafter.player_not_in_bank.ladder_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank.IsUsingFastRoute;

// import path.to.your.IsNearLadder
// import path.to.your.IsUsingFastRoute

/**
 * NOTES:
 * 
 */
public class InLadderArea extends BranchTask {

    private IsNearLadder isnearladder = new IsNearLadder();
    private IsUsingFastRoute isusingfastroute = new IsUsingFastRoute();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isusingfastroute;
    }

    @Override
    public TreeTask successTask() {
        return isnearladder;
    }
}
