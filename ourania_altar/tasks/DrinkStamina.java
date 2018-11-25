package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

import java.util.Date;
import java.util.regex.Pattern;

public class DrinkStamina extends Task {
    @Override
    public void execute() {
        System.out.println("Executing: DrinkStamina");

        Pattern stamina = Pattern.compile("Stamina pot.+");
        SpriteItem potion = Inventory.newQuery().names(stamina).results().first();
        if (potion != null) {
            if (potion.click()) {
                System.out.println("Drank Stamina");
                OuraniaAltar.staminaTimer = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean validate() {
        return (!Bank.isOpen() && OuraniaAltar.hasNoBrokenPouches() && OuraniaAltar.mustDrinkStamina());
    }
}
