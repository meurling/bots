package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * Option for teleporting/running back to bank
 */
public class WalkToBankFast extends LeafTask {

    private ZMI bot;
    public WalkToBankFast(ZMI bot) {
        this.bot =bot;
    }

    @Override
    public void execute() {
        bot.currentTaskString = "Manually walking to Bank (Fast route)";

        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bot.BANK_AREA);
        if (path!= null) {
            path.step();
        }
    }
}
