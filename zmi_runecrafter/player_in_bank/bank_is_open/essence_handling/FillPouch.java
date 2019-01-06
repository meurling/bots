package com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_open.essence_handling;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;

/**
 * NOTES:
 * 
 */
public class FillPouch extends LeafTask {

    private ZMI bot;
    private EssencePouch pouch;
    public FillPouch(ZMI bot, EssencePouch pouch) {
        this.bot = bot;
        this.pouch = pouch;
    }
    @Override
    public void execute() {
        bot.currentTaskString = "Filling pouch: " + pouch.getName();
        System.out.println("here0");
        if(Inventory.getItems(pouch.getName()).first().interact("Fill")){
            System.out.println("here1");
            Execution.delayUntil(() -> pouch.isFull(), 1000);
            System.out.println("here2");
        } else {
            // if we werent able to fill it, maybe its already full
            pouch.fillPouch();
            System.out.println("here3");
        }
    }
}
