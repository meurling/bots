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
public class WithdrawFood extends LeafTask {

    private ZMI bot;
    public WithdrawFood(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.currentTaskString = "Withdrawing food: " + bot.SETTING_FOOD;
        Bank.withdraw(bot.SETTING_FOOD, 1);
        Execution.delayUntil(() -> Inventory.contains(bot.SETTING_FOOD), 1400);
    }
}
