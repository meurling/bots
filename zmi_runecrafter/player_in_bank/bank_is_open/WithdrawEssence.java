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
public class WithdrawEssence extends LeafTask {

    private ZMI bot;
    public WithdrawEssence(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.currentTaskString = "Withdrawing essence";
        Bank.withdraw("Pure essence", 28);
        Execution.delayUntil(() -> Inventory.isFull(), 500);
    }
}
