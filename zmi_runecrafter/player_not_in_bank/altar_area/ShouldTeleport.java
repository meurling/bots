package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank.ShouldTakeFastRouteToBank;

// import path.to.your.Teleport
// import path.to.your.ShouldTakeFastRouteToBank

/**
 * NOTES:
 * Option for teleporting/running back to bank
 */
public class ShouldTeleport extends BranchTask {

    private ZMI bot;
    public ShouldTeleport(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldTakeFastRouteToBank(bot);
    }

    @Override
    public TreeTask successTask() {
        return new Teleport(bot);
    }
}
