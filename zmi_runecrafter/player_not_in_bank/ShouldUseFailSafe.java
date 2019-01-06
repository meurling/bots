package com.stixx.bots.zmi_runecrafter.player_not_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

public class ShouldUseFailSafe extends BranchTask {

    private ZMI bot;
    public ShouldUseFailSafe(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public TreeTask successTask() {
        return new FailSafe(bot);
    }

    @Override
    public boolean validate() {
        return bot.standingStillTimer.getRuntime() / 1000 > 20;
    }

    @Override
    public TreeTask failureTask() {
        return new RunEnabled(bot);
    }
}
