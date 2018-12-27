package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_in_bank.MustDepositRunes;

// import path.to.your.CanDepositInventory
// import path.to.your.MustDepositRunes

/**
 * NOTES:
 * 
 */
public class IsBankOpen extends BranchTask {

    private CanDepositInventory candepositinventory = new CanDepositInventory();
    private MustDepositRunes mustdepositrunes = new MustDepositRunes();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return mustdepositrunes;
    }

    @Override
    public TreeTask successTask() {
        return candepositinventory;
    }
}
