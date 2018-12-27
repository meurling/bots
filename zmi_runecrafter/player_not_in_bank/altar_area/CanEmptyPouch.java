package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.EmptyPouch
// import path.to.your.HasEssence

/**
 * NOTES:
 * 
 */
public class CanEmptyPouch extends BranchTask {

    private EmptyPouch emptypouch = new EmptyPouch();
    private HasEssence hasessence = new HasEssence();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return hasessence;
    }

    @Override
    public TreeTask successTask() {
        return emptypouch;
    }
}
