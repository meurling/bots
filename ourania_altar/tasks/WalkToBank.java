package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class WalkToBank extends Task {
    @Override
    public void execute() {
        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(OuraniaAltar.BANK_AREA);
        if (path!= null) {
            path.step();
        }
    }

    @Override
    public boolean validate() {
        Player player = Players.getLocal();
        return (!OuraniaAltar.BANK_AREA.contains(player) && !Inventory.isFull() && !OuraniaAltar.RC_AREA.contains(player));
    }
}
