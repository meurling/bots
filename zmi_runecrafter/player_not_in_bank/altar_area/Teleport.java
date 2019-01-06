package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * Option for teleporting/running back to bank
 */
public class Teleport extends LeafTask {

    private ZMI bot;
    public Teleport(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.currentTaskString="Teleporting";
        Magic.Lunar.OURANIA_TELEPORT.activate();
        Execution.delayUntil(() -> !bot.ALTAR_AREA.contains(bot.player), 3000);
    }
}
