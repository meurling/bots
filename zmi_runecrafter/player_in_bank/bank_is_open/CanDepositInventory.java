package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.DepositInventory
// import path.to.your.ShouldWithdrawStamina

/**
 * NOTES:
 * 
 */
public class CanDepositInventory extends BranchTask {

    private ZMI bot;
    public CanDepositInventory(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldWithdrawStamina(bot);
    }

    @Override
    public TreeTask successTask() {
        return new DepositInventory(bot);
    }
}
