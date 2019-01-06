package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class WalkToShortcut extends LeafTask {

    private ZMI bot;
    public WalkToShortcut(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public void execute() {
        bot.currentTaskString = "Travelling to Shortcut";
        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bot.SHORTCUT_AREA);
        if (path!= null) {
            path.step();
        }
    }
}
