package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.DepositInventory
// import path.to.your.ShouldWithdrawStamina

/**
 * NOTES:
 * 
 */
public class CanDepositInventory extends BranchTask {

    private DepositInventory depositinventory = new DepositInventory();
    private ShouldWithdrawStamina shouldwithdrawstamina = new ShouldWithdrawStamina();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return shouldwithdrawstamina;
    }

    @Override
    public TreeTask successTask() {
        return depositinventory;
    }
}
