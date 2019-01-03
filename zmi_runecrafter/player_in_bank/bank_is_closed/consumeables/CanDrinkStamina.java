package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.consumeables;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.OpenBank;

import java.util.regex.Pattern;

// import path.to.your.FillPouch
// import path.to.your.OpenBank

/**
 * NOTES:
 * 
 */
public class CanDrinkStamina extends BranchTask {

    private ZMI bot;
    public CanDrinkStamina(ZMI bot) {
        this.bot = bot;
    }


    @Override
    public boolean validate() {
        return Inventory.contains(Pattern.compile("Stamina .+"));
    }

    @Override
    public TreeTask failureTask() {
        return new OpenBank(bot);
    }

    @Override
    public TreeTask successTask() {
        return new DrinkStamina(bot);
    }
}
