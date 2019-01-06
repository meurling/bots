package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank.WalkToBankSlow;

// import path.to.your.ShouldStopFollow

/**
 * NOTES:
 *
 */
public class IsTravellingToAltarSlow extends BranchTask {

    private ZMI bot;
    public IsTravellingToAltarSlow(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return Inventory.contains("Pure essence");
    }

    @Override
    public TreeTask failureTask() {
        return new WalkToBankSlow(bot);
    }

    @Override
    public TreeTask successTask() {
        return new IsNearShortcut(bot);
    }
}
