package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class WithdrawNPCRunes extends LeafTask {

    private ZMI bot;
    public WithdrawNPCRunes(ZMI bot) {
        this.bot = bot;
    }
    @Override
    public void execute() {
        String[] runes ={"Air rune", "Cosmic rune", "Astral rune"};
        String missingRune = bot.helper.getMissingRunes(runes).get(0);
        bot.currentTaskString = "Withdrawing NPC-contact rune: " + missingRune;

        int amount;
        if (missingRune.equals("Air rune")) {
            amount = 2;
        } else amount = 1;
        Bank.withdraw(missingRune, amount);
        Execution.delayUntil(() -> Inventory.contains(missingRune), 1000);
    }
}
