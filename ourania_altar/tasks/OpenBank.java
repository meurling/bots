package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

import java.util.Date;
import java.util.regex.Pattern;

public class OpenBank extends Task {
    @Override
    public void execute() {
        System.out.println("Executing: OpenBank");

        Npc banker = Npcs.newQuery().names("Eniola").results().first();
        if (banker != null) {
            if (banker.isVisible() && !Bank.isOpen()) {
                banker.interact("Bank");
                System.out.println("Opened bank");
            } else {
                Camera.turnTo(banker);
            }
        }
        Execution.delay(142, 153);
    }

    @Override
    public boolean validate() {
        long staminaTime = ((new Date()).getTime() - OuraniaAltar.staminaTimer) / 1000;
        Pattern stamina = Pattern.compile("Stamina pot.+}");
        Player player = Players.getLocal();
        return (OuraniaAltar.BANK_AREA.contains(player) && !Inventory.isFull() && !Inventory.contains(OuraniaAltar.OPTION_FOOD) && !Bank.isOpen() && !canStillFillPouches() && OuraniaAltar.hasNoBrokenPouches() && !OuraniaAltar.mustDrinkStamina());
    }

    private boolean canStillFillPouches() {
        int essence = Inventory.newQuery().names("Pure essence").results().size();
        switch (OuraniaAltar.OPTION_RUNEPOUCH) {
            case 2:
                if (!OuraniaAltar.smallPouch.hasEssenceInPouch() && essence >= 3) {
                    return true;
                } else if (!OuraniaAltar.mediumPouch.hasEssenceInPouch() && essence >= 6) {
                    return true;
                }
                else if (!OuraniaAltar.largePouch.hasEssenceInPouch() && essence >= 9) {
                    return true;
                } else {
                    return false;
                }
                default: return false;
        }
    }
}
