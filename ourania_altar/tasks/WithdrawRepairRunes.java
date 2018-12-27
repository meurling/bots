package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class WithdrawRepairRunes extends Task {
    @Override
    public boolean validate() {
        return Bank.isOpen() && OuraniaAltar.shouldWithdrawRepairRunes() && !Inventory.isFull();
    }

    @Override
    public void execute() {
        OuraniaAltar.currentTaskString = "Withdrawing repair runes";
        if (!Inventory.contains("Air rune")) {
            Bank.withdraw("Air rune", 2);
            Execution.delayUntil(() -> Inventory.contains("Air rune"), 300);
        }
        if (!Inventory.contains("Cosmic rune")) {
            Bank.withdraw("Cosmic rune", 1);
            Execution.delayUntil(() -> Inventory.contains("Cosmic rune"), 300);
        }
        if (!Inventory.contains("Astral rune")) {
            Bank.withdraw("Astral rune", 1);
            Execution.delayUntil(() -> Inventory.contains("Astral rune"), 300);
        }
    }
}
