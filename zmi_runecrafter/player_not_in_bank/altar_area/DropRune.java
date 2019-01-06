package com.stixx.bots.zmi_runecrafter.player_not_in_bank.altar_area;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DropRune extends LeafTask {

    private ZMI bot;
    public DropRune(ZMI bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        String rune = getDropRune();
        if (rune != null) {
            System.out.println("Dropping rune: " + rune);
            dropItem(rune);
        } else {
            System.out.println("Drop item was null");
        }

    }

    private void dropItem(String rune) {
        SpriteItem drop = Inventory.newQuery().names(rune).results().first();
        Keyboard.pressKey(16);
        Execution.delay(30,60);
        drop.click();
        Execution.delay(10,90);
        Keyboard.releaseKey(16);
    }

    private String getDropRune() {
        List<String> droppableRunes = new ArrayList<String>();
        droppableRunes.add("Air rune");
        droppableRunes.add("Water rune");
        droppableRunes.add("Earth rune");
        droppableRunes.add("Fire rune");
        droppableRunes.add("Mind rune");
        droppableRunes.add("Body rune");
        String dropRune = null;


        for (int i = 0; i < droppableRunes.size(); i++) {
            for (int j = 0; j < bot.SETTING_INVENTORY_RUNES.size(); j++) {
                if (bot.SETTING_INVENTORY_RUNES.get(j).equals(droppableRunes.get(i))) {
                    droppableRunes.remove(i);
                }
            }
        }

        for (int i = 0; i < droppableRunes.size(); i++) {
            if (Inventory.contains(droppableRunes.get(i))){
                return droppableRunes.get(i);
            }
        }

        return dropRune;
    }
}
