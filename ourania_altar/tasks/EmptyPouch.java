package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.CustomObjects.EssencePouch;
import com.stixx.bots.ourania_altar.CustomObjects.Status;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class EmptyPouch extends Task{

    public EssencePouch[] essencePouches;
    public int option;

    private String giantPouch = "Giant pouch";
    private String largePouch = "Large pouch";
    private String mediumPouch = "Medium pouch";
    private String smallPouch = "Small pouch";

    public EmptyPouch() {
        this.essencePouches = OuraniaAltar.essencePouches;
        this.option = OuraniaAltar.OPTION_RUNEPOUCH;
    }
    @Override
    public boolean validate() {
        // make sure there are pouches to empty
        boolean valid = !GameObjects.newQuery().names("Runecrafting altar").results().isEmpty() && OuraniaAltar.RC_AREA.contains(Players.getLocal()) && OuraniaAltar.canEmptyPouch();
        return valid;
    }

    @Override
    public void execute() {
        System.out.println("Executing: EmptyPouch");
        switch (option) {
            case (0):
                // dont empty pouches
            case (1):
                // empty medium and small
            case (2):
                // empty upto large
                emptyPouch(2);
                if (!OuraniaAltar.largePouch.hasEssenceInPouch()) {
                    emptyPouch(0);
                    emptyPouch(1);
                }
            case (3):
                // empty upto giant

        }
    }
    private void emptyPouch(int essencePouch) {
        EssencePouch pouch = essencePouches[essencePouch];
        if (pouch.canEmptyPouch()) {
            Keyboard.pressKey(16); // hold shift
            if (Keyboard.isPressed(16)) {
                SpriteItem item = Inventory.newQuery().names(pouch.getName()).results().first();
                if (item.click()) {
                    OuraniaAltar.essencePouches[essencePouch].setHasEssenceInPouch(false);
                    System.out.println(pouch.getName() + " Was emptied");
                    Execution.delay(31, 49);
                    Keyboard.releaseKey(16);
                    Execution.delayUntil(() -> Inventory.contains("Pure essence"), 600, 900);
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
