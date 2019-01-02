package com.stixx.bots.zmi_runecrafter;

import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Dont do anything if following to altar
 */
public class EmptyLeaf extends LeafTask {

    private ZMI bot;
    public EmptyLeaf(ZMI bot) {
        this.bot =bot;
    }


    @Override
    public void execute() {
        System.out.println("Empty leaf reached");
    }
}
