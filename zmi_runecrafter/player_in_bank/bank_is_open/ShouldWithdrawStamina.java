package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

import java.util.regex.Pattern;

// import path.to.your.WithdrawStamina
// import path.to.your.ShouldWithdrawFood

/**
 * NOTES:
 * 
 */
public class ShouldWithdrawStamina extends BranchTask {

    private ZMI bot;
    public ShouldWithdrawStamina(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return bot.helper.timeForStamina() && !Inventory.contains(Pattern.compile("Stamina +."));
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldWithdrawFood(bot);
    }

    @Override
    public TreeTask successTask() {
        return new WithdrawStamina(bot);
    }
}
