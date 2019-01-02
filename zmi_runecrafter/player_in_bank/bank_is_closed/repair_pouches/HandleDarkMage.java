package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class HandleDarkMage extends LeafTask {

    private ZMI bot;
    public HandleDarkMage(ZMI bot) {
        this.bot = bot;
    }
    @Override
    public void execute() {
        if (ChatDialog.getTitle() != null) {
            bot.currentTaskString = "Handling Dark Mage";
            Keyboard.typeKey(32);
        }
    }
}
