package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class WalkToLadder extends Task {

    boolean clickedLadder = false;

    @Override
    public boolean validate() {
        Player player = Players.getLocal();
        return (OuraniaAltar.shouldTasksPause() && (OuraniaAltar.WALK_TO_LADDER_AREA.contains(player) || OuraniaAltar.LADDER_AREA.contains(player)));
    }

    @Override
    public void execute() {
        System.out.println("Executing: WalkToLadder");
        OuraniaAltar.currentTaskString = "walking to ladder";
        Player player = Players.getLocal();
        // walk to ladder
        if (OuraniaAltar.WALK_TO_LADDER_AREA.contains(player) && !player.isMoving()) {
            final Coordinate coordinate = OuraniaAltar.LADDER_AREA.getRandomCoordinate();
            RegionPath path = RegionPath.buildTo(coordinate);
            if (path != null) {
                path.step();
                System.out.println("Walking to LadderArea");
                Execution.delay(91, 140);
            }
        }
        // climb ladder
        else if (OuraniaAltar.LADDER_AREA.contains(player) && !GameObjects.newQuery().names("Ladder").actions("Climb").results().isEmpty()) {
            GameObject ladder = GameObjects.newQuery().names("Ladder").actions("Climb").results().first();
            if (ladder.isVisible()) {
                if (clickedLadder) {
                    clickedLadder = ladder.click();
                    System.out.println("Clicked Ladder");
                    Execution.delayUntil(() -> OuraniaAltar.BANK_AREA.contains(player));
                }
            }
            else {
                Camera.turnTo(ladder);
            }
        }
    }
}
