package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.details.Locatable;
import com.runemate.game.api.hybrid.local.hud.interfaces.Openable;
import com.runemate.game.api.hybrid.local.hud.interfaces.Tab;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.CustomObjects.EssencePouch;
import com.stixx.bots.ourania_altar.OuraniaAltar;

import java.awt.*;

public class Teleport extends Task {

    @Override
    public boolean validate() {
        Player player = Players.getLocal();

        boolean valid = (OuraniaAltar.allPouchesEmpty() && OuraniaAltar.RC_AREA.contains(player));
        return valid;
    }

    @Override
    public void execute() {
        System.out.println("Executing: Teleporting");
        Magic.Lunar.OURANIA_TELEPORT.activate();
        Execution.delayUntil(()->Players.getLocal().getAnimationId() == -1, 5000);
        Execution.delay(31, 41);
    }
}
