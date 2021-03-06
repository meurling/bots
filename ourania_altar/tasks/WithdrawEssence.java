package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

import java.util.Date;
import java.util.regex.Pattern;

public class WithdrawEssence extends Task {
    @Override
    public void execute() {
        System.out.println("Executing: WithdrwaingEssence");
        OuraniaAltar.currentTaskString = "Withdrawing essence";
        withdrawFood();
        withdrawEssence();
    }

    @Override
    public boolean validate() {
        if (OuraniaAltar.shouldTasksPause()) {
            Pattern runeRegex = Pattern.compile(".+ rune");
            if (OuraniaAltar.OPTION_PAYMENT_RUNE == "Rune pouch") {
                return (Bank.isOpen() && !Inventory.contains(runeRegex) && !Inventory.isFull());
            }
            else {
                return (Bank.isOpen() && Inventory.newQuery().names(runeRegex).results().size() <= 1 && !Inventory.isFull() && !OuraniaAltar.shouldWithdrawRepairRunes()); // must be bigger than 1 as we take the quick depoosit rune into consideration
            }
        }
        return false;
    }

    public void withdrawEssence() {
        if (Inventory.getItems().size() < 27)
        Bank.withdraw("Pure essence", 28);
        System.out.println("Withdrew essence");
    }


    public void withdrawFood() {
        Player player = Players.getLocal();
        if (Health.getCurrentPercent() < OuraniaAltar.OPTION_EAT_PERCENTAGE) {
            if (Bank.contains(OuraniaAltar.OPTION_FOOD) && !Inventory.contains(OuraniaAltar.OPTION_FOOD)) {
                Bank.withdraw(OuraniaAltar.OPTION_FOOD, 1);
                System.out.println("Withdrew Food");
            }
        }
        Execution.delay(95, 103);
    }
}
