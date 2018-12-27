package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.CraftRune
// import path.to.your.ShouldTeleport

/**
 * NOTES:
 * 
 */
public class HasEssence extends BranchTask {

    private CraftRune craftrune = new CraftRune();
    private ShouldTeleport shouldteleport = new ShouldTeleport();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return shouldteleport;
    }

    @Override
    public TreeTask successTask() {
        return craftrune;
    }
}
