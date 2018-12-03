package com.stixx.bots.ourania_altar;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.script.framework.task.TaskBot;
import com.stixx.bots.ourania_altar.CustomObjects.EssencePouch;
import com.stixx.bots.ourania_altar.Interface.Controller;
import com.stixx.bots.ourania_altar.Interface.Info;
import com.stixx.bots.ourania_altar.Interface.InfoInterface;
import com.stixx.bots.ourania_altar.Interface.UserInterface;
import com.stixx.bots.ourania_altar.tasks.*;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class OuraniaAltar extends TaskBot implements EmbeddableUI {

    public static EssencePouch smallPouch = new EssencePouch("Small pouch", 3);
    public static EssencePouch mediumPouch = new EssencePouch("Medium pouch", 6);
    public static EssencePouch largePouch = new EssencePouch("Large pouch", 9);
    public static EssencePouch giantPouch = new EssencePouch("Giant pouch", 12);
    public static EssencePouch[] essencePouches = {smallPouch, mediumPouch, largePouch, giantPouch};

    public static long staminaTimer;

    public static boolean guiWait = true;
    public static SimpleObjectProperty<Node> botInterfaceProperty;
    private static InfoInterface infoInterface;
    public static Info info;
    public static UserInterface userInterface; // configs

    public static boolean followingPlayer = false;
    public static Coordinate lastLocation;

    public static int OPTION_ESSENCEPOUCH = 2; // 0-no pouch | 1-medium | 2-large | 3-giant
    public static String OPTION_PAYMENT_RUNE = "Rune pouch";
    public static String OPTION_FOOD = "Monkfish";
    public static int OPTION_EAT_PERCENTAGE = 61;

    public static final Area WALK_TO_LADDER_AREA = new Area.Rectangular(new Coordinate(2452, 3236, 0), new Coordinate(2471, 3251, 0));
    public static final Area LADDER_AREA = new Area.Rectangular(new Coordinate(2452, 3230, 0), new Coordinate(2456, 3235, 0));
    public static final Area.Rectangular BANK_AREA = new Area.Rectangular(new Coordinate(3009, 5623, 0), new Coordinate(3020, 5630, 0));
    public static final Area.Rectangular RC_AREA = new Area.Rectangular(new Coordinate(3051, 5575, 0), new Coordinate(3064, 5583, 0));

    public static int rcExperienceGained;
    public static int moneyGained;
    public static String currentTaskString;

    public static StopWatch stopWatch = new StopWatch();


    @Override
    public void onStart(String... arguments) {
        super.onStart(arguments);
        currentTaskString = "Firing up the engines...";
        System.out.println("Bot Started!");
        staminaTimer = System.currentTimeMillis() - 100 * 1000;
        stopWatch.start();
        setLoopDelay(300, 600);
        rcExperienceGained = 0;
        moneyGained = 0;
        setEmbeddableUI(this);
        add(new UpdateUI(), new RepairPouch(), new CraftRune(), new ToggleRun(), new WalkToBank(), new EmptyPouch(), new Teleport(), new WalkToLadder(), new CloseBank(), new OpenBank(), new DepositInventory(), new FillPouch(), new WithdrawEssence(), new EatFood(), new DrinkStamina(), new WalkToAltar());
    }

    public static boolean shouldTasksPause() {
        return !guiWait;
    }



    public static boolean allPouchesEmpty() {
        switch (OPTION_ESSENCEPOUCH) {
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

    public static boolean canEmptyPouch() {
        switch (OPTION_ESSENCEPOUCH) {
            case 0:
                return false;
            case 1:
                return (smallPouch.canEmptyPouch() || mediumPouch.canEmptyPouch());
            case 2:
                return (smallPouch.canEmptyPouch() || mediumPouch.canEmptyPouch() || largePouch.canEmptyPouch());
            case 3:
                return (smallPouch.canEmptyPouch() || mediumPouch.canEmptyPouch() || largePouch.canEmptyPouch() || giantPouch.canEmptyPouch());
            default: return false;
        }
    }

    public static boolean hasNoBrokenPouches() {
        return !(Inventory.containsAnyOf(5513, 5511, 5515));
    }

    public static boolean mustDrinkStamina() {
        long staminaTime = ((new Date()).getTime() - OuraniaAltar.staminaTimer) / 1000;
        System.out.println("Stamina time: " + staminaTime);
        Pattern stamina = Pattern.compile("Stamina pot.+");
        boolean hasStamina = Inventory.contains(stamina);
        boolean valid = (hasStamina && !Bank.isOpen() && staminaTime > 100);
        return valid;
    }

    public static boolean allPouchesFull() {
        switch (OPTION_ESSENCEPOUCH) {
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

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            botInterfaceProperty = new SimpleObjectProperty<>(userInterface = new UserInterface(this));
        }
        return botInterfaceProperty;
    }

    public static void setToInfoProperty() {
        botInterfaceProperty.set(infoInterface);
        guiWait = false;
    }


    public static void updateInfo() {
        try {
            // Assign all values to a new instance of the Info class
            info = new Info(
                    (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), rcExperienceGained),  //   -   -   -   -   -   -   -   // xp per hour
                    (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), moneyGained),  //   -   -   -   -   -   -   -   // gp per hour
                    rcExperienceGained,
                    moneyGained,                //    -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   // Flax Picked
                    stopWatch.getRuntimeAsString(),                 //  -   -   -   -   -   -   -   -   -   -   -   -   -   -   // Total Runtime
                    currentTaskString);       //    -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   // Current Task

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Be sure to run infoUI.update() through runLater.
        // This will run infoUI.update() on the dedicated JavaFX thread which is the only thread allowed to update anything related to JavaFX rendering
        Platform.runLater(() -> infoInterface.update());

        /*
        *  "The way to think about it
            is that the "some stuff" is a package
            and you're at your house (the bot thread)
            Platform.runLater is the mailman
            you give the mailman your package and then go about your life however you want
            i.e. keep going in the code
            and then the mailman does what he needs with the package to get it delivered
            and it's no longer your or your house's problem"
            - The Wise. The One. The Arbiter.
         */
    }
}
