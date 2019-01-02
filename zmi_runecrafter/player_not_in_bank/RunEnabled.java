package com.stixx.bots.zmi_runecrafter.player_not_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area.InAltarArea;

// import path.to.your.InAltarArea
// import path.to.your.ShouldActivateRun

/**
 * NOTES:
 * 
 */
public class RunEnabled extends BranchTask {

    private ZMI bot;
    public RunEnabled(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldActivateRun(bot);
    }

    @Override
    public TreeTask successTask() {
        return new InAltarArea(bot);
    }
}
