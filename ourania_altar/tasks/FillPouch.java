package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.CustomObjects.EssencePouch;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class FillPouch extends Task {
    @Override
    public void execute() {
        System.out.println("Executing: FillPouch");

        int essenceInventory = Inventory.newQuery().names("Pure essence").results().size();
        if (essenceInventory > 0) {
            switch (OuraniaAltar.OPTION_RUNEPOUCH) {
                case 0:
                    System.out.println("No pouches to fill");
                case 2:
                    fillPouch(OuraniaAltar.largePouch);
                    fillPouch(OuraniaAltar.mediumPouch);
                    fillPouch(OuraniaAltar.smallPouch);
            }
        }

    }

    @Override
    public boolean validate() {
        int essenceInventory = Inventory.newQuery().names("Pure essence").results().size();
        return (essenceInventory > 0 && OuraniaAltar.BANK_AREA.contains(Players.getLocal()) && !OuraniaAltar.allPouchesFull() && !Bank.isOpen() && OuraniaAltar.hasNoBrokenPouches() && !OuraniaAltar.mustDrinkStamina());
    }

    private void fillPouch(EssencePouch pouch) {
        SpriteItem invPouch = Inventory.newQuery().names(pouch.getName()).results().first();
        int essenceInventory = Inventory.newQuery().names("Pure essence").results().size();
        if (pouch != null && invPouch != null) {
            if (!pouch.hasEssenceInPouch() && essenceInventory >= pouch.getCapacity()) {
                if (invPouch.click()) {
                    pouch.setHasEssenceInPouch(true);
                    System.out.println(pouch.getName() + " was filled");
                    Execution.delay(80, 180);

                }
            }
        }
    }
    private int essenceInventory() {
        return Inventory.newQuery().names("Pure essence").results().size();
    }
}
