package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class DepositInventory extends LeafTask {

    private ZMI bot;
    public DepositInventory(ZMI bot) {
        this.bot = bot;
    }
    @Override
    public void execute() {
        bot.currentTaskString = "Depositing inventory";
        Bank.depositInventory();
        Execution.delay(200,300);
    }
}
