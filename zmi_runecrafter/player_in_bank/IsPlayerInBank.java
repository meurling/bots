package com.stixx.bots.zmi_runecrafter.player_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.RunEnabled;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open.IsBankOpen;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.ShouldUseFailSafe;

// import path.to.your.IsBankOpen
// import path.to.your.RunEnabled

/**
 * NOTES:
 * Player in bank?
 */
public class IsPlayerInBank extends BranchTask {
    ZMI bot;
    public IsPlayerInBank(ZMI bot) {
        this.bot=bot;
    }

    @Override
    public boolean validate() {
        return bot.BANK_AREA.contains(bot.player);
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldUseFailSafe(bot);
    }

    @Override
    public TreeTask successTask() {
        return new IsBankOpen(bot);
    }
}
