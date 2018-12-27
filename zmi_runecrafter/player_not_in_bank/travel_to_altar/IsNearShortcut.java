package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

// import path.to.your.ClickShortcutToAltar
// import path.to.your.WalkToShortcut

/**
 * NOTES:
 * 
 */
public class IsNearShortcut extends BranchTask {

    private ClickShortcutToAltar clickshortcuttoaltar = new ClickShortcutToAltar();
    private WalkToShortcut walktoshortcut = new WalkToShortcut();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walktoshortcut;
    }

    @Override
    public TreeTask successTask() {
        return clickshortcuttoaltar;
    }
}
