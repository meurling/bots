package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.NpcContact;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class RepairPouch extends Task {
    @Override
    public void execute() {
        System.out.println("Execute: Reparing pouch");
        OuraniaAltar.currentTaskString = "Repairing pouches";

        Player player = Players.getLocal();
        ChatDialog.Continue cont = ChatDialog.getContinue();
        if (cont != null) {
            Keyboard.typeKey(32); // space
            Execution.delay(40, 60);
            Keyboard.releaseKey(32);
        }
        else if (player.getAnimationId() == -1 && !OuraniaAltar.hasNoBrokenPouches()) {
            NpcContact.cast(NpcContact.OSRS.DARK_MAGE);
            Execution.delayUntil(() -> Players.getLocal().getAnimationId() == -1, 5000);
        }
    }

    @Override
    public boolean validate() {
        boolean hasRunes = true;
        if (OuraniaAltar.OPTION_PAYMENT_RUNE != "Rune pouch") {
            hasRunes = Inventory.containsAllOf("Cosmic rune", "Air rune", "Astral rune");
        }
        return OuraniaAltar.shouldTasksPause() && !OuraniaAltar.hasNoBrokenPouches() && !Bank.isOpen() && OuraniaAltar.BANK_AREA.contains(Players.getLocal()) && hasRunes || ChatDialog.getContinue() != null;
    }
}
