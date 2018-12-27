package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches.CanRepairPouch;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches.HandleDarkMage;

// import path.to.your.HandleDarkMage
// import path.to.your.CanRepairPouch

/**
 * NOTES:
 * 
 */
public class IsContactingDarkMage extends BranchTask {

    private HandleDarkMage handledarkmage = new HandleDarkMage();
    private CanRepairPouch canrepairpouch = new CanRepairPouch();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return canrepairpouch;
    }

    @Override
    public TreeTask successTask() {
        return handledarkmage;
    }
}
