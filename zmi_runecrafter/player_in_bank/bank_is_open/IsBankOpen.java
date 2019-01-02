package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.MustDepositRunes;

// import path.to.your.CanDepositInventory
// import path.to.your.MustDepositRunes

/**
 * NOTES:
 * 
 */
public class IsBankOpen extends BranchTask {
    private ZMI bot;
    public IsBankOpen(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return new MustDepositRunes(bot);
    }

    @Override
    public TreeTask successTask() {
        return new CanDepositInventory(bot);
    }
}
