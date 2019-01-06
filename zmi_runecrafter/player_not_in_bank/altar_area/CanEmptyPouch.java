package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.EmptyPouch
// import path.to.your.HasEssence

/**
 * NOTES:
 * 
 */
public class CanEmptyPouch extends BranchTask {

    private ZMI bot;
    public CanEmptyPouch(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return bot.helper.getEmptyablePouches().size() > 0;
    }

    @Override
    public TreeTask failureTask() {
        return new HasEssence(bot);
    }

    @Override
    public TreeTask successTask() {
        return new EmptyPouch(bot);
    }
}
