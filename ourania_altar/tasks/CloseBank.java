package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class CloseBank extends Task {
    @Override
    public void execute() {
        System.out.println("Close bank");
        OuraniaAltar.currentTaskString = "Close bank";

        if (Bank.isOpen()) {
            boolean bankClosed = Bank.close(true);
            Execution.delay(83, 108);
            if (bankClosed) {
                System.out.println("Closed bank");
            } else {
                Bank.close();
            }
        }
    }

    @Override
    public boolean validate() {

        return (Bank.isOpen() && Inventory.isFull() && !OuraniaAltar.allPouchesFull()) || (!OuraniaAltar.hasNoBrokenPouches() && Inventory.containsAllOf("Cosmic rune", "Air rune", "Astral rune"));
    }
}
