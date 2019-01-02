package com.stixx.bots.zmi_runecrafter.player_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.MustEatFood;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches.IsContactingDarkMage;

// import path.to.your.IsContactingDarkMage
// import path.to.your.MustEatFood

/**
 * NOTES:
 * 
 */
public class MustRepairPouch extends BranchTask {

    private ZMI bot;
    public MustRepairPouch(ZMI bot) {
        this.bot = bot;
    }


    @Override
    public boolean validate() {
        return bot.helper.hasDegradedPouch();
    }

    @Override
    public TreeTask failureTask() {
        return new MustEatFood(bot);
    }

    @Override
    public TreeTask successTask() {
        return new IsContactingDarkMage(bot);
    }
}
