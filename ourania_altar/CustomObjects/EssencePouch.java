package com.stixx.bots.ourania_altar.CustomObjects;

public class EssencePouch {
    private boolean hasEssenceInPouch = false;
    private String name;
    private int capacity;
    public EssencePouch(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;

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
