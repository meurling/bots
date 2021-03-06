package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

// import path.to.your.ClickShortcutToAltar
// import path.to.your.WalkToShortcut

/**
 * NOTES:
 * 
 */
public class IsNearShortcut extends BranchTask {

    private ZMI bot;
    public IsNearShortcut(ZMI bot) {
        this.bot =bot;
    }

    @Override
    public boolean validate() {
        return bot.SHORTCUT_AREA.contains(bot.player) && Inventory.contains("Pure essence");
    }

    @Override
    public TreeTask failureTask() {
        return new WalkToShortcut(bot);
    }

    @Override
    public TreeTask successTask() {
        return new ClickShortcut(bot);
    }
}
