package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.ChooseAltarTravelMethod;

public class ShouldTravelToAltar extends BranchTask {
    private ZMI bot;
    public ShouldTravelToAltar(ZMI bot) {
    this.bot = bot;
    }

    @Override
    public TreeTask failureTask() {
        return new OpenBank(bot);
    }

    @Override
    public TreeTask successTask() {
        return new ChooseAltarTravelMethod(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.isFull() && bot.helper.getEmptyPouches().size() == 0;
    }
}
