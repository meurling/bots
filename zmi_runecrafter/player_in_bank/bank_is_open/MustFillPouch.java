package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.OpenBank;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.ShouldTravelToAltar;

public class MustFillPouch extends BranchTask {

    private ZMI bot;
    public MustFillPouch(ZMI bot) {
        this.bot = bot;
    }
    @Override
    public TreeTask failureTask() {
        return new ShouldTravelToAltar(bot);
    }

    @Override
    public TreeTask successTask() {
        return new OpenBank(bot);
    }

    @Override
    public boolean validate() {
        return bot.helper.getEmptyPouches().size() > 0;
    }
}
