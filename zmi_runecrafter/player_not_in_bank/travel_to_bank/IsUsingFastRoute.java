package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.IsFollowingPlayer;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.IsNearShortcut;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.IsTravellingToAltarFast;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.IsTravellingToAltarSlow;

// import path.to.your.IsFollowingPlayer
// import path.to.your.IsNearShortcut

/**
 * NOTES:
 * If we get here, the player is between the bank and altar
 */
public class IsUsingFastRoute extends BranchTask {

    private ZMI bot;
    public IsUsingFastRoute(ZMI bot) {
        this.bot =bot;
    }

    @Override
    public boolean validate() {
        return bot.SETTING_USE_FAST_ROUTE;
    }

    @Override
    public TreeTask failureTask() {
        return new IsTravellingToAltarSlow(bot);
    }

    @Override
    public TreeTask successTask() {
        return new IsTravellingToAltarFast(bot);
    }
}
