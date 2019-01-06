package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

public class ShouldDropRune extends BranchTask {

    private ZMI bot;
    public ShouldDropRune(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public TreeTask successTask() {
        return new DropRune(bot);
    }

    @Override
    public boolean validate() {
        int emptySpace = 28 - Inventory.getItems().size();
        for (int i = 0; i < bot.helper.getFullPouches().size(); i++) {
            if (bot.helper.getFullPouches().get(i).getCapacity() > emptySpace) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldTeleport(bot);
    }
}
