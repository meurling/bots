package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.OpenBank;

// import path.to.your.CastNpcContact
// import path.to.your.OpenBank

/**
 * NOTES:
 * 
 */
public class CanRepairPouch extends BranchTask {

    private ZMI bot;
    public CanRepairPouch(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        String[] runes ={"Air rune", "Cosmic rune", "Astral rune"};
        return bot.helper.getMissingRunes(runes).size() == 0;
    }

    @Override
    public TreeTask failureTask() {
        return new OpenBank(bot);
    }

    @Override
    public TreeTask successTask() {
        return new CastNpcContact(bot);
    }
}
