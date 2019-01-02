package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.ladder_area.InLadderArea;

// import path.to.your.CanEmptyPouch
// import path.to.your.InLadderArea

/**
 * NOTES:
 * 
 */
public class InAltarArea extends BranchTask {

    private ZMI bot;
    public InAltarArea(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new InLadderArea(bot);
    }

    @Override
    public TreeTask successTask() {
        return new CanEmptyPouch(bot);
    }
}
