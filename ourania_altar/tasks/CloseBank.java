package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class CloseBank extends Task {
    @Override
    public void execute() {
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

        return (Bank.isOpen() && Inventory.isFull());
    }
}
