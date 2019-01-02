package com.stixx.bots.barrows;

import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varp;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceContainer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.queries.results.InterfaceComponentQueryResults;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.LoopingScript;
import com.runemate.game.api.script.framework.listeners.VarbitListener;
import com.runemate.game.api.script.framework.listeners.VarpListener;
import com.runemate.game.api.script.framework.listeners.events.VarbitEvent;
import com.runemate.game.api.script.framework.listeners.events.VarpEvent;

import java.util.ArrayList;
import java.util.List;

public class Barrows extends LoopingScript {

    int counter = 0;
    List<InterfaceComponent> diffs = new ArrayList<>();
    InterfaceComponentQueryResults old = null;

    @Override
    public void onStart(String... arguments) {
        super.onStart(arguments);
        setLoopDelay(100);
    }

    @Override
    public void onLoop() {
        System.out.println("chat title" + ChatDialog.getTitle());
        // System.out.println("Looping");
        /**
        InterfaceComponent left = Interfaces.getAt(25, 6);
        InterfaceComponent middle = Interfaces.getAt(25, 7);
        InterfaceComponent right = Interfaces.getAt(25, 8);

        if (left != null) {
            System.out.println("Left: " + left.toString() + " Id: " + left.getId() + " spriteId: " + left.getSpriteId() + " hash: " +left.hashCode());
        } else {
            System.out.println("Not visible");
        }

        if (middle != null) {
            System.out.println("middle: " + middle.toString() + " Id: " + middle.getId() + " spriteId: " + middle.getSpriteId());
        }
        if (right != null) {
            System.out.println("right: " + right.toString() + " Id: " + right.getId() + " spriteId: " + right.getSpriteId());
        }
*/
    }
}
