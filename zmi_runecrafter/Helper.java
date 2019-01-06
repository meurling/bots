package com.stixx.bots.zmi_runecrafter;

import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.osrs.local.RunePouch;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Helper {
    public ZMI bot;

    public Helper(ZMI bot) {
        this.bot = bot;
    }

    public List<EssencePouch> getEmptyablePouches() {
        List<EssencePouch> fullPouches = getFullPouches();
        List<EssencePouch> pouches = new ArrayList<EssencePouch>();
            for (int i = 0; i < fullPouches.size(); i++) {
                if (canEmptyPouch(fullPouches.get(i))) {
                    System.out.println("Pouch is emptyable: " + fullPouches.get(i).getName());
                    pouches.add(fullPouches.get(i));
                }
            }
        return pouches;
    }

    public boolean canEmptyPouch(EssencePouch pouch) {
        return pouch.getCapacity() <= 28 - Inventory.getItems().size();
    }

    public List<EssencePouch> getEmptyPouches() {
        List<EssencePouch> emptyPouches = new ArrayList<EssencePouch>();
        for (int i = 0; i < bot.SETTING_ESSENCE_POUCHES.size(); i++) {
            if (!bot.SETTING_ESSENCE_POUCHES.get(i).isFull()) {
                emptyPouches.add(bot.SETTING_ESSENCE_POUCHES.get(i));
            }
        }
        return emptyPouches;
    }

    public List<EssencePouch> getFullPouches() {
        List<EssencePouch> fullPouches = new ArrayList<EssencePouch>();
        for (int i = 0; i < bot.SETTING_ESSENCE_POUCHES.size(); i++) {
            if (bot.SETTING_ESSENCE_POUCHES.get(i).isFull()) {
                fullPouches.add(bot.SETTING_ESSENCE_POUCHES.get(i));
            }
        }
        return fullPouches;
    }



    public boolean timeForFood() {
        return Health.getCurrentPercent() < 50;
    }

    public boolean timeForStamina() {
        if (bot.SETTING_USE_STAMINA) {
            return !bot.staminaActive || Traversal.getRunEnergy() < 19 || (bot.staminaTimer.getRuntime() /1000 > 105 && Traversal.getRunEnergy() < 85);

        } else return false;
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
            if (bot.SETTING_INVENTORY_RUNES.contains(rune) && Inventory.contains(rune)){
                found = true;
            } else if (bot.SETTING_USE_RUNEPOUCH) {
                if (RunePouch.Slot.ONE.getRune().getName().equals(rune)) {
                    found = true;
                } else if (RunePouch.Slot.TWO.getRune().getName().equals(rune)) {
                    found = true;
                } else if (RunePouch.Slot.THREE.getRune().getName().equals(rune)) {
                    found = true;
                }
            }
            for (int j = 0; j < bot.SETTING_STAFF_RUNES.size(); j++) {
                if (bot.SETTING_STAFF_RUNES.get(j).equals(rune)) {
                    found = true;
                }
            }
            if (!found) {
                missing.add(rune);
            }
        }
        return missing;
    }
}
