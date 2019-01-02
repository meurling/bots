package com.stixx.bots.zmi_runecrafter.player_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.consumeables.CanDrinkStamina;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.essence_handling.HasEmptyPouches;

// import path.to.your.CanDrinkStamina
// import path.to.your.HasEmptyPouches

/**
 * NOTES:
 * 
 */
public class MustDrinkStamina extends BranchTask {

    private ZMI bot;
    public MustDrinkStamina(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new HasEmptyPouches(bot);
    }

    @Override
    public TreeTask successTask() {
        return new CanDrinkStamina(bot);
    }
}
