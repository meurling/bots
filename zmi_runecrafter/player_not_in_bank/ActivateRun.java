package com.stixx.bots.zmi_runecrafter.player_not_in_bank;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class ActivateRun extends LeafTask {

    private ZMI bot;
    public ActivateRun(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public void execute() {
        Traversal.toggleRun();
        Execution.delay(100,200);
    }
}
