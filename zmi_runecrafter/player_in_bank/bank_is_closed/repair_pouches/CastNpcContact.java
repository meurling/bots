package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches;

import com.runemate.game.api.hybrid.local.hud.interfaces.NpcContact;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class CastNpcContact extends LeafTask {
    private ZMI bot;
    public CastNpcContact(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.currentTaskString = "Casting NPC Contact";
        NpcContact.cast(NpcContact.OSRS.DARK_MAGE);
        Execution.delay(300, 400);
    }
}
