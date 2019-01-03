package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open.essence_handling.CanFillPouch;

// import path.to.your.WithdrawNPCRunes
// import path.to.your.ShouldWithdrawEssence

/**
 * NOTES:
 * 
 */
public class ShouldWithdrawNPCRunes extends BranchTask {

    private ZMI bot;
    public ShouldWithdrawNPCRunes(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        String[] runes ={"Air rune", "Cosmic rune", "Astral rune"};
        return bot.helper.hasDegradedPouch() && bot.helper.getMissingRunes(runes).size() > 0;
    }

    @Override
    public TreeTask failureTask() {
        return new CanFillPouch(bot);
    }

    @Override
    public TreeTask successTask() {
        return new WithdrawNPCRunes(bot);
    }
}
