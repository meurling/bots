package com.stixx.bots.rune_picker;

import com.runemate.game.api.hybrid.entities.*;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.queries.NpcQueryBuilder;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.LoopingScript;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.*;


public class RunePicker extends LoopingScript {

    public Rune[] runes = {
            new Rune(565, "Blood rune", 342),
            new Rune(563, "Law rune", 350),
            new Rune(560, "Death rune", 277),
            new Rune(561, "Nature rune", 274),
            new Rune(9075, "Astral rune", 233),
            new Rune(564, "Cosmic rune", 176),
            new Rune(566, "Soul rune", 146),
            new Rune(562, "Chaos rune", 104),
            new Rune(556, "Air rune", 5),
            new Rune(555, "Water rune", 5),
            new Rune(554, "Fire rune", 5),
            new Rune(557, "Earth rune", 5),
            new Rune(559, "Body rune", 5),
    };

    int lowestX = 3058;
    int lowestY = 5577;
    int highestX = 3062;
    int highestY = 5581;

    int[][] grid = new int[5][5];

    long startTime;
    long gridTimer;
    long lastRuneTime;

    Pattern runePattern = Pattern.compile(".+ rune");
    Player player = Players.getLocal();
    @Override
    public void onStart(String... $) {
        super.onStart($);
        setLoopDelay(30,50);
        startTime = System.currentTimeMillis();
        gridTimer = System.currentTimeMillis();
        lastRuneTime = System.currentTimeMillis();
    }

    @Override
    public void onStop() {
        super.onStop();
        long elapsed = ((new Date()).getTime() - startTime) / 60000;
        System.out.println("Elapsed time: " + elapsed + " minutes");
    }

    @Override
    public void onLoop() {
        System.out.println("Looping: " + Players.getLocal().getPosition().toString());

        //clickOneSquare();
        runAroundVersion();

    }

    public void clickOneSquare() {
        updateGrid();
        Coordinate coordinate = getBestCoordinate();
        clickSquare(coordinate);
        resetGrid();
    }

    public void runAroundVersion() {
        checkInArea();
        GroundItem bestRune = findBestRune();
        pickUpRune(bestRune);
    }

    public void handleRun() {
        if (!Traversal.isRunEnabled()) {
            if (Traversal.getRunEnergy() > 20) {
                Traversal.toggleRun();
            }
        }
    }

    public void clickSquare(Coordinate coordinate) {
        Player player = Players.getLocal();
        GroundItem highestValueItem = null;
        int highestValue = 0;
        Iterator<GroundItem> groundItemIterator = GroundItems.newQuery().names(runePattern).on(coordinate).results().iterator();
        while(groundItemIterator.hasNext()) {
            GroundItem item = groundItemIterator.next();
            int quantity = item.getQuantity();
            int totalValue = quantity * getRunePrice(item.getId());
            if (totalValue > highestValue) {
                highestValueItem = item;
                highestValue = totalValue;
            }
        }

        if (player.getPosition() == coordinate) {
            if (Npcs.newQuery().actions("Talk-to").results().isEmpty()) {
                if (highestValueItem != null) {
                    highestValueItem.click();
                }
            } else {
                if (highestValueItem != null) {
                    highestValueItem.interact("Take");
                }
            }
        }
        // walk to square if not there
        else if (player.getAnimationId() == -1) {
            final RegionPath path = RegionPath.buildTo(coordinate);
            if (path != null) {
                path.step();
            }
        }
    }

    // reset every 5 minutes
    public void resetGrid() {
        long elapsed = ((new Date()).getTime() - gridTimer) / 60000;
        if (elapsed > 5) {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    grid[y][x] = 0;
                }
            }
        }
    }

    public Coordinate getBestCoordinate() {
        int highestValue = 0;
        int highX = 0;
        int highY = 0;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (grid[y][x] > highestValue) {
                    highestValue = grid[x][y];
                    highX = x;
                    highY = y;
                }
            }
        }
        Coordinate bestCoord = new Coordinate(highX + lowestX, highY + lowestY, 0);
        System.out.println("Best Coordinate: " + bestCoord.toString());
        return  bestCoord;
    }

    public void updateGrid() {
        Iterator<GroundItem> groundItemIterator = GroundItems.newQuery().names(runePattern).results().iterator();
        while(groundItemIterator.hasNext()) {
            GroundItem item = groundItemIterator.next();
            int quantity = item.getQuantity();
            int totalValue = quantity * getRunePrice(item.getId());
            if (totalValue > 50) {
                int x = item.getPosition().getX();
                int y = item.getPosition().getY();
                if (x <= highestX && x >= lowestX && y <= highestY && y >= lowestY) {
                    int xGrid = x - lowestX;
                    int yGrid = y - lowestY;
                    grid[yGrid][xGrid] += totalValue;
                }
            }
        }
    }

    public void pickUpRune(GroundItem rune) {
        if (rune != null) {
            try {
                int xPos = rune.getPosition().getX();
                int yPos = rune.getPosition().getY();
                if (xPos > 3056 && xPos < 3064 && yPos < 5583 && yPos > 5575) {
                    boolean clickedRune = false;
                    if (Npcs.newQuery().actions("Talk-to").results().isEmpty()) {
                        clickedRune = rune.click();
                    } else {
                        clickedRune = rune.interact("Take");

                    }
                    if (!clickedRune) {
                        clickedRune = rune.interact("Take");
                    }
                    if (!clickedRune) {
                        long elapsed = ((new Date()).getTime() - lastRuneTime) / 1000;
                        if (elapsed > 8) {
                            SpriteItem item = Inventory.getItems().shuffle().get(0);
                            item.hover();
                        }
                    } else {
                        lastRuneTime = System.currentTimeMillis();
                    }
                }
            } catch (NullPointerException e) {

            }

        }
    }

    public void checkInArea() {
        Player player = Players.getLocal();
        int xPos = player.getPosition().getX();
        int yPos = player.getPosition().getY();
        if (!(xPos > 3056 && xPos < 3064 && yPos < 5583 && yPos > 5575)) {
            final Area rcArea = new Area.Rectangular(new Coordinate(3056, 5575, 0), new Coordinate(3064, 5583));
            final Coordinate destination = rcArea.getRandomCoordinate();
            final RegionPath path = RegionPath.buildTo(destination);
            if (path != null) {
                path.step();
            }
        }
    }

    public GroundItem findBestRune() {

        GroundItem highestValueItem = null;
        int highestValue = 0;
        Iterator<GroundItem> groundItemIterator = GroundItems.newQuery().names(runePattern).results().iterator();
        while(groundItemIterator.hasNext()) {
            GroundItem item = groundItemIterator.next();
            int quantity = item.getQuantity();
            int totalValue = quantity * getRunePrice(item.getId());
            if (totalValue > highestValue) {
                highestValueItem = item;
                highestValue = totalValue;
            }
            System.out.println("ITem: " + item.distanceTo(player.getPosition()));
        }
        if (highestValue < 100) {
            return null;
        }
        return highestValueItem;
    }

    public int getRunePrice(int id) {
        for (int i = 0; i < runes.length ; i++) {
            Rune rune = runes[i];
            if (rune.id == id) {
                return rune.price;
            }
        }
        return 0;
    }
}
