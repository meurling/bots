package com.stixx.bots.zmi_runecrafter.custom_objects;

public class EssencePouch {
    private boolean hasEssenceInPouch = false;
    private String name;
    private int capacity;

    public EssencePouch(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
