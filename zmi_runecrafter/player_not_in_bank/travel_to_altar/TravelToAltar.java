package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class TravelToAltar extends LeafTask {

    private ZMI bot;
    public TravelToAltar(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public void execute() {

        bot.currentTaskString = "Manually walking to Altar (Fast route)";

        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bot.ALTAR_AREA);
        if (path!= null) {
            path.step();
        }
    }
}
