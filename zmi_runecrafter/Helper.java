package com.stixx.bots.zmi_runecrafter;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.osrs.local.RunePouch;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Helper {
    public ZMI bot;

    public Helper(ZMI bot) {
        this.bot = bot;
    }

    public boolean inventoryHasUnwantedRunes() {
        int runes = Inventory.newQuery().names(Pattern.compile(".+ rune")).results().size();
        int diff = runes - bot.SETTING_INVENTORY_RUNES.size();
        return diff > 0;
    }

    public boolean hasDegradedPouch() {
        return (Inventory.containsAnyOf(5513, 5511, 5515));
    }

    public List<String> getMissingRunes(String[] runes) {
        List<String> missing = new ArrayList<String>();

        for (int i = 0; i < runes.length; i++) {
            String rune = runes[i];
            boolean found = false;
            if (bot.SETTING_INVENTORY_RUNES.contains(rune)){
                found = true;
            } else if (bot.SETTING_USE_RUNEPOUCH) {
                if (RunePouch.Slot.ONE.getRune().getName().equals(rune)) {
                    found = true;
                } else if (RunePouch.Slot.TWO.getRune().getName().equals(rune)) {
                    found = true;
                } else if (RunePouch.Slot.THREE.getRune().getName().equals(rune)) {
                    found = true;
                }
            } else if (bot.SETTING_STAFF_RUNES.contains(rune)) {
                found = true;
            }
            if (!found) {
                missing.add(rune);
            }
        }
        return missing;
    }
}
