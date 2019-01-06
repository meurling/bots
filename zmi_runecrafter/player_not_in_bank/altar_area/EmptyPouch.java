package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;

import java.util.List;

/**
 * NOTES:
 * 
 */
public class EmptyPouch extends LeafTask {

    private ZMI bot;
    public EmptyPouch(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public void execute() {
        List<EssencePouch> emptyAblePouches = bot.helper.getEmptyablePouches();
        int inventorySize = Inventory.getItems().size();
        EssencePouch chosenPouch = null;
        int biggest = 0;
        if (emptyAblePouches.size() > 0) {
            for (int i = 0; i < emptyAblePouches.size(); i++) {
                EssencePouch pouch = emptyAblePouches.get(i);
                if (pouch.getCapacity() > biggest) {
                    chosenPouch = pouch;
                }
            }
        }

        // empty here
        if (chosenPouch != null) {
            bot.currentTaskString = "Emptying: " + chosenPouch.getName();
            SpriteItem thePouch = Inventory.newQuery().names(chosenPouch.getName()).results().first();
            if (thePouch != null) {
                if (!Keyboard.isPressed(16)) {
                    Keyboard.pressKey(16); // SHIFT
                }
                if (thePouch.click()) {
                    Execution.delayUntil(() -> Inventory.getItems().size() > inventorySize, 500,670);
                    chosenPouch.emptyPouch();
                    System.out.println("BOT EMPTIED: " + chosenPouch.getName());
                    if (bot.helper.getFullPouches().size() == 0) {
                        Keyboard.releaseKey(16);
                    }
                }
            }
        } else {
            System.out.println("ERRor, CanEmptyPouch let through pouches that werent able to empty!!");
        }
    }
}
