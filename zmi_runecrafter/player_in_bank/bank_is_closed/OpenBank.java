package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class OpenBank extends LeafTask {

    private ZMI bot;
    public OpenBank(ZMI bot) {
        this.bot =bot;
    }

    @Override
    public void execute() {
        bot.currentTaskString = "Opening bank";
        Npc banker = Npcs.newQuery().names("Eniola").results().first();
        if (banker != null) {
            if (!banker.isVisible()) {
                Camera.turnTo(banker);
            }
            banker.interact("Bank");
        }
    }
}
