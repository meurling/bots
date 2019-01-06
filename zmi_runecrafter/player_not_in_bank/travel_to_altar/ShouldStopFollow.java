package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.EmptyLeaf;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.TravelToAltar
// import path.to.your.EmptyLeaf

/**
 * NOTES:
 * shoulds top following if standing still or running wrong diretion
 */
public class ShouldStopFollow extends BranchTask {

    private ZMI bot;
    public ShouldStopFollow(ZMI bot) {
        this.bot =bot;
    }

    @Override
    public boolean validate() {
        return !bot.player.isMoving() || bot.player.isFacing(bot.BANK_AREA);
    }

    @Override
    public TreeTask failureTask() {
        return new EmptyLeaf(bot, "ShouldStopFollow");
    }

    @Override
    public TreeTask successTask() {
        return new TravelToAltar(bot);
    }
}
