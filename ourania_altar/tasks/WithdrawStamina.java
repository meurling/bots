package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

import java.util.Date;
import java.util.regex.Pattern;

public class WithdrawStamina extends Task {
    @Override
    public boolean validate() {
        Pattern stamina = Pattern.compile("Stamina pot.+");
        long staminaTime = ((new Date()).getTime() - OuraniaAltar.staminaTimer) / 1000;
        return Bank.isOpen() && !Inventory.isFull() && !Inventory.contains(stamina) && (staminaTime > 100 || Traversal.getRunEnergy() < 20);
    }

    @Override
    public void execute() {
        OuraniaAltar.currentTaskString = "Withdrawing stamina";
        Pattern stamina = Pattern.compile("Stamina pot.+");
        Bank.withdraw(stamina, 1);
        System.out.println("Withdrew Stamina");
    }
}
