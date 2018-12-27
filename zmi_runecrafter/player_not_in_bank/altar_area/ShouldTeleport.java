package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank.ShouldTakeFastRouteToBank;

// import path.to.your.Teleport
// import path.to.your.ShouldTakeFastRouteToBank

/**
 * NOTES:
 * Option for teleporting/running back to bank
 */
public class ShouldTeleport extends BranchTask {

    private Teleport teleport = new Teleport();
    private ShouldTakeFastRouteToBank shouldtakefastroutetobank = new ShouldTakeFastRouteToBank();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return shouldtakefastroutetobank;
    }

    @Override
    public TreeTask successTask() {
        return teleport;
    }
}
