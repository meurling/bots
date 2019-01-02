package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches.CanRepairPouch;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.repair_pouches.HandleDarkMage;

// import path.to.your.HandleDarkMage
// import path.to.your.CanRepairPouch

/**
 * NOTES:
 * 
 */
public class IsContactingDarkMage extends BranchTask {

    private ZMI bot;
    public IsContactingDarkMage(ZMI bot) {
        this.bot = bot;
    }


    @Override
    public boolean validate() {
        boolean castingNPC = bot.player.getAnimationId() == 4413;
        if (ChatDialog.getTitle() != null) {
            System.out.println("Chat titel: " + ChatDialog.getTitle());
            return castingNPC || ChatDialog.getTitle().equals("Dark Mage") || ChatDialog.getTitle().equals(bot.player.getName());

        } else {
            return castingNPC;
        }
    }

    @Override
    public TreeTask failureTask() {
        return new CanRepairPouch(bot);
    }

    @Override
    public TreeTask successTask() {
        return new HandleDarkMage(bot);
    }
}
