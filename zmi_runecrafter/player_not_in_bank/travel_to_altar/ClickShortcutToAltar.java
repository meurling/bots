package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

/**
 * NOTES:
 * 
 */
public class ClickShortcutToAltar extends LeafTask {

    private ZMI bot;
    public ClickShortcutToAltar(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public void execute() {
        GameObject shortcut = GameObjects.newQuery().names("Crack").actions("Squeeze-through").results().nearest();
        if (shortcut!=null) {
            bot.currentTaskString="Using shortcut to altar";
            if (shortcut.interact("Squeeze-through")){
                Execution.delayUntil(() -> bot.ALTAR_AREA.contains(bot.player));
                Execution.delay(150,230);
            }
        }
    }
}
