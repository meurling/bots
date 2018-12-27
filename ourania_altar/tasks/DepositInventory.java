package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

import java.util.regex.Pattern;

public class DepositInventory extends Task {
    @Override
    public void execute() {
        System.out.println("Executing: DepositInventory");
        OuraniaAltar.currentTaskString = "Depositing inventory";
        if (Bank.isOpen()) {
            Bank.depositInventory();
            System.out.println("Deposited inventory");
        }
    }

    @Override
    public boolean validate() {
        Pattern runeRegex = Pattern.compile(".+ rune");
        if (OuraniaAltar.OPTION_PAYMENT_RUNE == "Rune pouch") {
            return (Bank.isOpen() && Inventory.contains(runeRegex));
        }
        else {
            return Bank.isOpen() && Inventory.newQuery().names(runeRegex).results().size() > 1 && OuraniaAltar.hasNoBrokenPouches(); // must be bigger than 1 as we take the quick depoosit rune into consideration
        }
    }
}
