package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.CraftRune
// import path.to.your.ShouldTeleport

/**
 * NOTES:
 * 
 */
public class HasEssence extends BranchTask {

    private ZMI bot;
    public HasEssence(ZMI bot) {
        this.bot =bot;
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Pure essence");
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldDropRune(bot);
    }

    @Override
    public TreeTask successTask() {
        return new CraftRune(bot);
    }
}
