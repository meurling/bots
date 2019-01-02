package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.WithdrawFood
// import path.to.your.ShouldWithdrawNPCRunes

/**
 * NOTES:
 * 
 */
public class ShouldWithdrawFood extends BranchTask {

    private ZMI bot;
    public ShouldWithdrawFood(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return bot.helper.timeForFood() && !Inventory.contains(bot.SETTING_FOOD);
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldWithdrawNPCRunes(bot);
    }

    @Override
    public TreeTask successTask() {
        return  new WithdrawFood(bot);
    }
}
