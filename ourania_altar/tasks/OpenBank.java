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

import java.util.regex.Pattern;

public class OpenBank extends Task {
    @Override
    public void execute() {
        System.out.println("Executing: OpenBank");

        Npc banker = Npcs.newQuery().names("Eniola").results().first();
        if (banker != null) {
            if (banker.isVisible()) {
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
        Pattern stamina = Pattern.compile("Stamina pot\\.+}");
        Player player = Players.getLocal();
        return (OuraniaAltar.BANK_AREA.contains(player) && !Inventory.isFull() && !Inventory.contains(stamina) && !Inventory.contains(OuraniaAltar.OPTION_FOOD) && !Bank.isOpen());
    }
}
