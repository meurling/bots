package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class WalkToBankSlow extends LeafTask {

    private ZMI bot;
    public WalkToBankSlow(ZMI bot) {
        this.bot =bot;
    }

    @Override
    public void execute() {
        bot.currentTaskString = "Manually walking to Bank";

        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bot.BANK_AREA);
        if (path!= null) {
            path.step();
        }
    }
}
