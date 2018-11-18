package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class EatFood extends Task {
    @Override
    public void execute() {
        System.out.println("Executing: EatFood");

        SpriteItem food = Inventory.newQuery().names(OuraniaAltar.OPTION_FOOD).results().first();
        if (food != null) {
            if (food.click()) {
                System.out.println("Ate food");
            }

        }
    }

    @Override
    public boolean validate() {
        return (Inventory.contains(OuraniaAltar.OPTION_FOOD) && !Bank.isOpen());
    }
}
