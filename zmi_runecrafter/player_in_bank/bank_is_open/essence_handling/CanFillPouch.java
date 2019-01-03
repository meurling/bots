package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open.essence_handling;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open.CloseBank;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open.ShouldWithdrawEssence;

import java.util.List;

// import path.to.your.FillPouch
// import path.to.your.OpenBank

/**
 * NOTES:
 * 
 */
public class CanFillPouch extends BranchTask {
    private ZMI bot;
    private EssencePouch fillPouch;
    public CanFillPouch(ZMI bot) {
        this.bot = bot;
    }



    @Override
    public boolean validate() {
        int pureEssInventory = Inventory.getQuantity("Pure essence");
        List<EssencePouch> emptyPouches = bot.helper.getEmptyPouches();
        for (int i = 0; i < emptyPouches.size(); i++) {
            EssencePouch pouch = emptyPouches.get(i);
            if (pureEssInventory >= pouch.getCapacity()) {
                fillPouch = pouch;
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldWithdrawEssence(bot);
    }

    @Override
    public TreeTask successTask() {
        return new FillPouch(bot, fillPouch);
    }
}
