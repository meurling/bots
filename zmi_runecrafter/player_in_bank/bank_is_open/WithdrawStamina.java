package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

import java.util.regex.Pattern;

/**
 * NOTES:
 * 
 */
public class WithdrawStamina extends LeafTask {

    private ZMI bot;
    public WithdrawStamina(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.currentTaskString = "Withdrawing Stamina";
        if (Inventory.isFull()) {
            Bank.depositInventory();
            Execution.delayUntil(() -> !Inventory.isFull(), 400);
        }
        Bank.withdraw(Pattern.compile("Stamina .+"), 1);
        Execution.delayUntil(() -> Inventory.contains(Pattern.compile("Stamina .+")), 1200);
    }
}
