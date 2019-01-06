package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area.CraftRune;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.ladder_area.InLadderArea;
import com.stixx.bots.zmi_runecrafter.player_not_in_bank.ladder_area.IsNearLadder;

public class AltarVisible extends BranchTask {

    private ZMI bot;
    public AltarVisible(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public TreeTask successTask() {
        return new CraftRune(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Pure essence") && bot.LAST_AREA.contains(bot.player);
    }

    @Override
    public TreeTask failureTask() {
        return new InLadderArea(bot);
    }
}
