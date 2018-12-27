package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.IsFollowingPlayer;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.IsNearShortcut;

// import path.to.your.IsFollowingPlayer
// import path.to.your.IsNearShortcut

/**
 * NOTES:
 * If we get here, the player is between the bank and altar
 */
public class IsUsingFastRoute extends BranchTask {

    private IsFollowingPlayer isfollowingplayer = new IsFollowingPlayer();
    private IsNearShortcut isnearshortcut = new IsNearShortcut();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isnearshortcut;
    }

    @Override
    public TreeTask successTask() {
        return isfollowingplayer;
    }
}
