package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.CustomObjects.EssencePouch;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class EmptyPouch extends Task{


    private String giantPouch = "Giant pouch";
    private String largePouch = "Large pouch";
    private String mediumPouch = "Medium pouch";
    private String smallPouch = "Small pouch";


    @Override
    public boolean validate() {
        // make sure there are pouches to empty
        boolean valid = OuraniaAltar.RC_AREA.contains(Players.getLocal()) && OuraniaAltar.canEmptyPouch() && OuraniaAltar.shouldTasksPause();
        return valid;
    }

    @Override
    public void execute() {
        System.out.println("Executing: EmptyPouch");
        switch (OuraniaAltar.OPTION_ESSENCEPOUCH) {
            case (0):
                // dont empty pouches
            case (1):
                // empty medium and small
                emptyPouch(1);
                emptyPouch(0);
            case (2):
                // empty upto large
                emptyPouch(2);
                if (!OuraniaAltar.largePouch.hasEssenceInPouch()) {
                    emptyPouch(0);
                    emptyPouch(1);
                }
            case (3):
                // empty upto giant
                emptyPouch(3);
                emptyPouch(0);
                emptyPouch(2);
                emptyPouch(1);
        }
    }
    private void emptyPouch(int essencePouch) {
        EssencePouch pouch = OuraniaAltar.essencePouches[essencePouch];
        if (pouch.canEmptyPouch()) {
            Keyboard.pressKey(16); // hold shift
            if (Keyboard.isPressed(16)) {
                SpriteItem item = Inventory.newQuery().names(pouch.getName()).results().first();
                if (item.click()) {
                    OuraniaAltar.essencePouches[essencePouch].setHasEssenceInPouch(false);
                    System.out.println(pouch.getName() + " Was emptied");
                    Keyboard.releaseKey(16);
                    Execution.delay(100, 300);
                }
            }
        }
    }

    private int essenceInventory() {
        return Inventory.newQuery().names("Pure essence").results().size();
    }

    private void craftRune() {
        CraftRune craftRune = new CraftRune();
        if (craftRune.validate()) {
            craftRune.craftRune();
        }
    }
}
