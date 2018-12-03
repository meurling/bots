package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class WalkToAltar extends Task {
    private static final Area.Rectangular firstArea = new Area.Rectangular(new Coordinate(3013, 5618, 0), new Coordinate(3016, 5622, 0));
    private static final Area.Rectangular secondArea = new Area.Rectangular(new Coordinate(3013, 5604, 0), new Coordinate(3016, 5617, 0));
    private static final Area.Rectangular thirdArea = new Area.Rectangular(new Coordinate(3013, 5587, 0), new Coordinate(3016, 5605, 0));
    private static final Area.Rectangular between34 = new Area.Rectangular(new Coordinate(3013, 5587, 0), new Coordinate(3016, 5605, 0));
    private static final Area.Rectangular fourthArea = new Area.Rectangular(new Coordinate(3013, 5587, 0), new Coordinate(3016, 5605, 0));
    private static final Area.Rectangular fifthArea = new Area.Rectangular(new Coordinate(3016, 5578, 0), new Coordinate(3026, 5579, 0)); // roundish area to crater
    private static final Area.Rectangular sixthArea = new Area.Rectangular(new Coordinate(3027, 5578, 0), new Coordinate(3037, 5582, 0));
    private static final Area.Rectangular finalArea = new Area.Rectangular(new Coordinate(3045, 5577, 0), new Coordinate(3050, 5579, 0));




    private Area.Rectangular[] areas = {firstArea, secondArea, thirdArea, fourthArea, fifthArea, sixthArea, finalArea, OuraniaAltar.RC_AREA};

    @Override
    public boolean validate() {
        return (OuraniaAltar.shouldTasksPause() && Inventory.isFull() && !OuraniaAltar.RC_AREA.contains(Players.getLocal()) && OuraniaAltar.allPouchesFull() && OuraniaAltar.hasNoBrokenPouches() && !OuraniaAltar.mustDrinkStamina());
    }
    @Override
    public void execute() {
        System.out.println("Walking to altar");
        Player player = Players.getLocal();

        if (Bank.isOpen()) {
            webWalk();
        } else {
            if (OuraniaAltar.BANK_AREA.contains(player)) {
                Player followPlayer = Players.newQuery().moving().reachable().within(firstArea).results().nearest();
                Camera.turnTo(firstArea);
                if (followPlayer != null) {
                    followPlayer.interact("Follow");
                    OuraniaAltar.followingPlayer = true;
                    Execution.delay(200, 250);
                }
            }

            if (OuraniaAltar.followingPlayer) {
                follow();
            } else {
                webWalk();
            }
        }
    }

    private void webWalk() {
        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(OuraniaAltar.RC_AREA);
        if (path!= null) {
            path.step();
        }
    }

    private void walk() {
        System.out.println("Executing: WalkingToAltar");

        Player player = Players.getLocal();
        Area.Rectangular destinationArea = getDestinationArea();

        if (destinationArea != null) {
            RegionPath path = RegionPath.buildTo(destinationArea.getRandomCoordinate());
            if (path != null) {
                path.step();
                System.out.println("Walking to altar");
                Execution.delay(291, 321);
            }
        }
    }

    private void follow() {
        System.out.println("Executing: FollowingToAltar");
        Player player = Players.getLocal();
        if (!OuraniaAltar.BANK_AREA.contains(player)) {
            if (player.isFacing(OuraniaAltar.BANK_AREA) || !player.isMoving()) {
                OuraniaAltar.followingPlayer = false; // stop following if changed direction!
            }
        }
    }

    private Area.Rectangular getDestinationArea() {
        Player player = Players.getLocal();
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();

        Area.Rectangular dest = finalArea;
        for (int i = 0; i < areas.length; i++) {
            if (areas[i].isVisible()) {
                dest = areas[i];
            }
        }
        return dest;

        /*
        if (y > firstArea.getTopLeft().getY()) {
            return firstArea;
        }
        if (y > secondArea.getTopLeft().getY()) {
            return secondArea;
        }
        if (y > thirdArea.getTopLeft().getY()) {
            return thirdArea;
        }
        if (y > fourthArea.getTopLeft().getY()) {
            return fourthArea;
        }
        if (x < fifthArea.getBottomLeft().getX()) {
            return fifthArea;
        }
        if (x < sixthArea.getBottomLeft().getX()) {
            return sixthArea;
        }
        if (x < finalArea.getBottomLeft().getX()) {
            return finalArea;
        }
        if (finalArea.contains(player)) {
            return OuraniaAltar.RC_AREA;
        }
        System.out.println("Couldnt get correct destination");
        return finalArea;*/
    }

}
