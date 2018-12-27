package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.OpenBank;

// import path.to.your.CastNpcContact
// import path.to.your.OpenBank

/**
 * NOTES:
 * 
 */
public class CanRepairPouch extends BranchTask {

    private CastNpcContact castnpccontact = new CastNpcContact();
    private OpenBank openbank = new OpenBank();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return openbank;
    }

    @Override
    public TreeTask successTask() {
        return castnpccontact;
    }
}
