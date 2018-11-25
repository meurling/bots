package com.stixx.bots.ourania_altar.CustomObjects;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;

public class EssencePouch {
    private boolean hasEssenceInPouch = false;
    private String name;
    private int capacity;
    public EssencePouch(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;

    }

    public boolean canEmptyPouch() {
        return (hasEssenceInPouch && 28 - capacity - Inventory.getItems().size() >= 0);
    }

    public boolean hasEssenceInPouch() {
        return hasEssenceInPouch;
    }

    public void setHasEssenceInPouch(boolean bool) {
        hasEssenceInPouch = bool;
    }

    public int getCapacity() {
        return  capacity;
    }

    public String getName() {
        return name;
    }
}
