package com.stixx.bots.zmi_runecrafter.player_not_in_bank;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.TravelToAltar;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_bank.WalkToBankFast;

public class FailSafe extends LeafTask {
    private ZMI bot;
    public FailSafe(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        System.out.println("Failsafe activated");
        if (Inventory.contains("Pure essence")) {
            bot.currentTaskString = "FailSafe activated: Walking to altar.";
            TravelToAltar travelToAltar = new TravelToAltar(bot);
            travelToAltar.execute();
        } else {
            bot.currentTaskString = "FailSafe activated: Walking to altar.";
            WalkToBankFast walkToBankFast = new WalkToBankFast(bot);
            walkToBankFast.execute();
        }
    }
}
