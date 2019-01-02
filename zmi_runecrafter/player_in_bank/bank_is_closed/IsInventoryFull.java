package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.ChooseAltarTravelMethod;

// import path.to.your.ChooseAltarTravelMethod
// import path.to.your.OpenBank

/**
 * NOTES:
 * 
 */
public class IsInventoryFull extends BranchTask {

    private ZMI bot;
    public IsInventoryFull(ZMI bot) {
        this.bot = bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new OpenBank(bot);
    }

    @Override
    public TreeTask successTask() {
        return new ChooseAltarTravelMethod(bot);
    }
}
