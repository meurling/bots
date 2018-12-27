package com.stixx.bots.zmi_runecrafter.player_not_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area.InAltarArea;

// import path.to.your.InAltarArea
// import path.to.your.ShouldActivateRun

/**
 * NOTES:
 * 
 */
public class RunEnabled extends BranchTask {

    private InAltarArea inaltararea = new InAltarArea();
    private ShouldActivateRun shouldactivaterun = new ShouldActivateRun();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return shouldactivaterun;
    }

    @Override
    public TreeTask successTask() {
        return inaltararea;
    }
}
