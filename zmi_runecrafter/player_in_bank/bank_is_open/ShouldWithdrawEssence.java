package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.WithdrawEssence
// import path.to.your.CloseBank

/**
 * NOTES:
 * 
 */
public class ShouldWithdrawEssence extends BranchTask {

    private ZMI bot;
    public ShouldWithdrawEssence(ZMI bot) {
        this.bot = bot;
    }
    @Override
    public boolean validate() {
        return !Inventory.isFull();
    }

    @Override
    public TreeTask failureTask() {
        return new CloseBank(bot);
    }

    @Override
    public TreeTask successTask() {
        return new WithdrawEssence(bot);
    }
}
