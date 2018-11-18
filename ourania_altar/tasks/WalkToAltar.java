package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class WalkToAltar extends Task {
    private static final Area.Rectangular firstArea = new Area.Rectangular(new Coordinate(3013, 5618, 0), new Coordinate(3016, 5622, 0));
    private static final Area.Rectangular secondArea = new Area.Rectangular(new Coordinate(3013, 5604, 0), new Coordinate(3016, 5617, 0));
    private static final Area.Rectangular thirdArea = new Area.Rectangular(new Coordinate(3013, 5587, 0), new Coordinate(3016, 5605, 0));
    private static final Area.Rectangular fourthArea = new Area.Rectangular(new Coordinate(3013, 5587, 0), new Coordinate(3016, 5605, 0));
    private static final Area.Rectangular fifthArea = new Area.Rectangular(new Coordinate(3016, 5578, 0), new Coordinate(3026, 5579, 0)); // roundish area to crater
    private static final Area.Rectangular sixthArea = new Area.Rectangular(new Coordinate(3027, 5578, 0), new Coordinate(3037, 5582, 0));
    private static final Area.Rectangular finalArea = new Area.Rectangular(new Coordinate(3045, 5577, 0), new Coordinate(3050, 5579, 0));

    private Area.Rectangular[] areas = {OuraniaAltar.RC_AREA, firstArea, secondArea, thirdArea, fourthArea, fifthArea, sixthArea, finalArea, OuraniaAltar.RC_AREA};

    @Override
    public boolean validate() {
        return (Inventory.isFull() && !Bank.isOpen() && !OuraniaAltar.RC_AREA.contains(Players.getLocal()));
    }
    @Override
    public void execute() {
        System.out.println("Executing: WalkingToAltar");

        Player player = Players.getLocal();
        if (OuraniaAltar.followingPlayer) {

        } else {
            walk();
        }
    }

    private void walk() {
        Player player = Players.getLocal();
        Area.Rectangular destinationArea = null;
        for (int i = 0; i < areas.length - 1; i++) {
            if (areas[i].contains(player)) {
                destinationArea = areas[i++];
            }
        }
        if (destinationArea != null) {
            RegionPath path = RegionPath.buildTo(destinationArea);
            if (path != null) {
                path.step();
                System.out.println("Walking to altar");
                Execution.delay(510, 731);
            }
        }
    }

    private void follow() {

    }

}
