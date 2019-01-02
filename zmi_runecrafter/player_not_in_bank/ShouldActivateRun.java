package com.stixx.bots.zmi_runecrafter.player_not_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.ActivateRun;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area.InAltarArea;

// import path.to.your.ActivateRun
// import path.to.your.InAltarArea

/**
 * NOTES:
 * 
 */
public class ShouldActivateRun extends BranchTask {

    private ZMI bot;
    public ShouldActivateRun(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new InAltarArea(bot);
    }

    @Override
    public TreeTask successTask() {
        return new ActivateRun(bot);
    }
}
