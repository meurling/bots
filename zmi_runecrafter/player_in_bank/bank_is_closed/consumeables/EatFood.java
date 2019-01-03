package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.consumeables;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class EatFood extends LeafTask {

    private ZMI bot;
    public EatFood(ZMI bot) {
        this.bot = bot;
    }
    @Override
    public void execute() {
        bot.currentTaskString = "Eating food";
        Inventory.getItems(bot.SETTING_FOOD).first().click();
        Execution.delay(50,150);
    }
}
