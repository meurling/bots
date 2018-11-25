package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.CustomObjects.Status;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class CraftRune extends Task {

    @Override
    public boolean validate() {
        Player player = Players.getLocal();
        boolean valid = (Inventory.contains("Pure essence") && !GameObjects.newQuery().names("Runecrafting altar").actions("Craft-rune").results().isEmpty()) && OuraniaAltar.RC_AREA.contains(player) && !OuraniaAltar.canEmptyPouch();
        return valid;
    }

    @Override
    public void execute() {
        System.out.println("Execute: Craft Rune");
        craftRune();
    }

    public void craftRune() {
        GameObject altar = GameObjects.newQuery().names("Runecrafting altar").actions("Craft-rune").results().nearest();
        if (altar != null) {
            if (!altar.isVisible()){
                Camera.turnTo(altar);
            } else if (altar.interact("Craft-rune")) {
                Execution.delayUntil(()->Players.getLocal().getAnimationId() == -1, 5000);
                Execution.delay(500, 600);
            }
        }
    }
}
