package com.stixx.bots.zmi_runecrafter;

import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_in_bank.IsPlayerInBank;

/**
 * NOTES:
 * waiting
 */
public class Root extends BranchTask {

    ZMI bot;

    public Root(ZMI bot) {
        this.bot = bot;
    }

    IsPlayerInBank isPlayerInBank = new IsPlayerInBank(bot);

    @Override
    public TreeTask failureTask() {
        bot.player = Players.getLocal();
        bot.updateInfo();
        return isPlayerInBank;
    }

    @Override
    public TreeTask successTask() {
        System.out.println("Waiting for configs");
        return new EmptyLeaf();
    }

    @Override
    public boolean validate() {
        return bot.waitingForGUI;
    }
}
