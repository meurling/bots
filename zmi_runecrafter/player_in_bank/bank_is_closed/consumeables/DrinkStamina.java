package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.consumeables;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

import java.util.regex.Pattern;

/**
 * NOTES:
 *
 */
public class DrinkStamina extends LeafTask {

    private ZMI bot;
    public DrinkStamina(ZMI bot) {
        this.bot = bot;
    }
    @Override
    public void execute() {
        bot.currentTaskString = "Drinking Stamina, cheers!";
        if (Inventory.getItems(Pattern.compile("Stamina .+")).first().click()) {
            Execution.delay(200,300);
        }
    }
}
