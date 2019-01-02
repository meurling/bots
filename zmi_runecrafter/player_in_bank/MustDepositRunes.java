package com.stixx.bots.zmi_runecrafter.player_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.OpenBank;

// import path.to.your.OpenBank
// import path.to.your.MustRepairPouch

/**
 * NOTES:
 * 
 */
public class MustDepositRunes extends BranchTask {
    private ZMI bot;
    public MustDepositRunes(ZMI bot) {
        this.bot = bot;
    }


    @Override
    public boolean validate() {
        return bot.helper.inventoryHasUnwantedRunes();
    }

    @Override
    public TreeTask failureTask() {
        return new MustRepairPouch(bot);
    }

    @Override
    public TreeTask successTask() {
        return new OpenBank(bot);
    }
}
