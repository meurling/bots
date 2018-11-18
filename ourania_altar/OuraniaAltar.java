package com.stixx.bots.ourania_altar;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.task.TaskBot;
import com.stixx.bots.ourania_altar.CustomObjects.EssencePouch;
import com.stixx.bots.ourania_altar.CustomObjects.Status;
import com.stixx.bots.ourania_altar.tasks.*;

public class OuraniaAltar extends TaskBot {

    public static EssencePouch smallPouch = new EssencePouch("Small pouch", 3);
    public static EssencePouch mediumPouch = new EssencePouch("Medium pouch", 6);
    public static EssencePouch largePouch = new EssencePouch("Large pouch", 9);
    public static EssencePouch giantPouch = new EssencePouch("Giant pouch", 12);
    public static EssencePouch[] essencePouches = {smallPouch, mediumPouch, largePouch, giantPouch};

    public static long staminaTimer = System.currentTimeMillis();

    public static boolean followingPlayer = false;

    public static int OPTION_RUNEPOUCH = 2; // 0-no pouch | 1-medium | 2-large | 3-giant
    public static String OPTION_PAYMENT_RUNE = "Rune pouch";
    public static String OPTION_FOOD = "Monkfish";
    public static int OPTION_EAT_PERCENTAGE = 61;

    public static final Area WALK_TO_LADDER_AREA = new Area.Rectangular(new Coordinate(2452, 3236, 0), new Coordinate(2471, 3251, 0));
    public static final Area LADDER_AREA = new Area.Rectangular(new Coordinate(2452, 3230, 0), new Coordinate(2456, 3235, 0));
    public static final Area.Rectangular BANK_AREA = new Area.Rectangular(new Coordinate(3009, 5623, 0), new Coordinate(3020, 5630, 0));
    public static final Area.Rectangular RC_AREA = new Area.Rectangular(new Coordinate(3051, 5575, 0), new Coordinate(3064, 5583, 0));


    @Override
    public void onStart(String... arguments) {
        super.onStart(arguments);

        System.out.println("Bot Started!");
        setLoopDelay(250, 500);
        add(new CraftRune(), new EmptyPouch(), new Teleport(), new WalkToLadder(), new CloseBank(), new OpenBank(), new DepositInventory(), new FillPouch(), new WithdrawEssence(), new EatFood(), new DrinkStamina(), new WalkToAltar());
    }

    public static boolean allPouchesEmpty() {
        switch (OPTION_RUNEPOUCH) {
            case 0:
                return true;
            case 1:
                if (!smallPouch.hasEssenceInPouch() && !mediumPouch.hasEssenceInPouch()) {
                    return true;
                }
            case 2:
                if (!smallPouch.hasEssenceInPouch() && !mediumPouch.hasEssenceInPouch() && !largePouch.hasEssenceInPouch()) {
                    return true;
                }
            case 3:
                if (!smallPouch.hasEssenceInPouch() && !mediumPouch.hasEssenceInPouch() && !largePouch.hasEssenceInPouch() && !giantPouch.hasEssenceInPouch()) {
                return true;
            }
            default: return false;
        }
    }

    public static boolean allPouchesFull() {
        switch (OPTION_RUNEPOUCH) {
            case 0:
                return true;
            case 1:
                if (smallPouch.hasEssenceInPouch() && mediumPouch.hasEssenceInPouch()) {
                    return true;
                }
            case 2:
                if (smallPouch.hasEssenceInPouch() && mediumPouch.hasEssenceInPouch() && largePouch.hasEssenceInPouch()) {
                    return true;
                }
            case 3:
                if (smallPouch.hasEssenceInPouch() && mediumPouch.hasEssenceInPouch() && largePouch.hasEssenceInPouch() && giantPouch.hasEssenceInPouch()) {
                    return true;
                }
            default: return false;
        }
    }
}
