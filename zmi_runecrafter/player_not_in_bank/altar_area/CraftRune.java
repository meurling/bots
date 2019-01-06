package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.ourania_altar.tasks.WalkToAltar;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar.TravelToAltar;

/**
 * NOTES:
 * 
 */
public class CraftRune extends LeafTask {

    private ZMI bot;
    public CraftRune(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public void execute() {
        GameObject altar = GameObjects.newQuery().names("Runecrafting altar").actions("Craft-rune").results().nearest();
        if (altar!=null) {
            if (!altar.isVisible()){
                Camera.turnTo(altar);
                if (!altar.isVisible()) {
                    TravelToAltar travelToAltar = new TravelToAltar(bot);
                    travelToAltar.execute();
                }
            } else if (altar.interact("Craft-rune")) {
                Execution.delayUntil(()->!Inventory.contains("Pure essence"), 2000);
                Execution.delay(600,1000);
            }
        }
    }
}
