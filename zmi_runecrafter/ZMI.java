package com.stixx.bots.zmi_runecrafter;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.script.framework.listeners.SkillListener;
import com.runemate.game.api.script.framework.listeners.VarbitListener;
import com.runemate.game.api.script.framework.listeners.VarpListener;
import com.runemate.game.api.script.framework.listeners.events.SkillEvent;
import com.runemate.game.api.script.framework.listeners.events.VarbitEvent;
import com.runemate.game.api.script.framework.listeners.events.VarpEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.Interface.Info;
import com.stixx.bots.zmi_runecrafter.Interface.InfoController;
import com.stixx.bots.zmi_runecrafter.Interface.InfoInterface;
import com.stixx.bots.zmi_runecrafter.Interface.UserInterface;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZMI extends TreeBot implements EmbeddableUI, VarbitListener, VarpListener, SkillListener {

    //---------------BRANCHES---------------//
    //---------------BRANCHES---------------//


    //---------------SETTINGS---------------//
        public List<EssencePouch> SETTING_ESSENCE_POUCHES = new ArrayList<EssencePouch>();
        public List<String> SETTING_STAFF_RUNES = new ArrayList<String>();
        public List<String> SETTING_INVENTORY_RUNES = new ArrayList<String>();
        public boolean SETTING_USE_STAMINA;
        public boolean SETTING_USE_FAST_ROUTE;
        public boolean SETTING_USE_RUNEPOUCH;
        public boolean SETTING_USE_TELEPORT;
        public String SETTING_FOOD;

    //---------------SETTINGS---------------//

    //---------------RUN_TIME---------------//
        public int levelsGained = 0;
        public int magicLevelsGained;
        public int experienceGained = 0;
        public int moneyGained;
        public String currentTaskString = "Lighting the engines...";
        public int magicExperienceGained;
        public Helper helper;
    //---------------RUN_TIME---------------//

    //---------------GUIs---------------//
        public boolean waitingForGUI = true;
        public SimpleObjectProperty<Node> botInterfaceProperty;
        private InfoInterface infoInterface;
        public UserInterface userInterface; // configs
        public Info info;
        public InfoController infoController;
    //---------------GUIs---------------//

    //---------------VARIABLES---------------//
        public Player player;
        public StopWatch staminaTimer = new StopWatch();
        public boolean staminaActive = false;
        public StopWatch runTime = new StopWatch();
        public EssencePouch Giant_Pouch = new EssencePouch("Giant pouch", 12);
        public EssencePouch Large_Pouch = new EssencePouch("Large pouch", 9);
        public EssencePouch Medium_Pouch = new EssencePouch("Medium pouch", 6);
        public EssencePouch Small_Pouch = new EssencePouch("Small pouch", 3);
        public boolean followingPlayer = false;
        public StopWatch bankStandingTimer = new StopWatch();
        public StopWatch standingStillTimer = new StopWatch();
    //---------------VARIABLES---------------//

    //---------------AREAS---------------//
        public Area BANK_AREA = new Area.Rectangular(new Coordinate(3009, 5624, 0), new Coordinate(3021, 5630, 0));
        public Area FIRST_AREA = new Area.Rectangular(new Coordinate(3013, 5619, 0), new Coordinate(3015, 5623, 0));
        public Area ALTAR_AREA = new Area.Rectangular(new Coordinate(3055, 5574, 0), new Coordinate(3062, 5586, 0));
        public Area SHORTCUT_AREA = new Area.Rectangular(new Coordinate(3050, 5587, 0), new Coordinate(3053, 5588, 0));
        public Area LAST_AREA = new Area.Rectangular(new Coordinate(3048, 5574, 0), new Coordinate(3054, 5579, 0));

    //---------------AREAS---------------//

    @Override
    public void onStart(String... arguments) {
        super.onStart(arguments);
        currentTaskString = "Starting up...";
        botInterfaceProperty = null;
        helper = new Helper(this);
        setEmbeddableUI(this);
        staminaTimer.start();
        getEventDispatcher().addListener(this);
        bankStandingTimer.start();
        standingStillTimer.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("Small full: " + Small_Pouch.isFull());
        System.out.println("Medium full: " + Medium_Pouch.isFull());
        System.out.println("Large full: " + Large_Pouch.isFull());
        System.out.println("Giant full: " + Giant_Pouch.isFull());

    }

    @Override
    public TreeTask createRootTask() {
        return new Root(this);
    }


    @Override
    public void onExperienceGained(SkillEvent event) {
        System.out.println("Gained experience!");
        if (event != null) {
            if (event.getSkill() == Skill.RUNECRAFTING) {
                experienceGained += event.getChange();
            }
        }
    }

    @Override
    public void onLevelUp(SkillEvent event) {
        System.out.println("Gained Level!");
        if (event != null) {
            if (event.getSkill() == Skill.RUNECRAFTING) {
                levelsGained++;
            }
        }
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            botInterfaceProperty = new SimpleObjectProperty<>(userInterface = new UserInterface(this));
            infoInterface = new InfoInterface(this);
        }
        return botInterfaceProperty;
    }

    public void setToInfoProperty() {
        System.out.println("Setting InfoUI");
        System.out.println("Settings:");
        System.out.println("Stamina: " + SETTING_USE_STAMINA);
        System.out.println("Ivnentory: " + SETTING_INVENTORY_RUNES.toString());
        System.out.println("STAFF: " + SETTING_STAFF_RUNES.toString());
        System.out.println("Pouches: " + SETTING_ESSENCE_POUCHES.toString());
        System.out.println("RunePouch: " + SETTING_USE_RUNEPOUCH);
        System.out.println("Route: " + SETTING_USE_FAST_ROUTE);
        System.out.println("Food: " + SETTING_FOOD);
        System.out.println("Teleport: " + SETTING_USE_TELEPORT);
        botInterfaceProperty.set(infoInterface);
    }

    public void updateInfo() {
        try {
            info = new Info(
                    experienceGained,
                    levelsGained,
                    currentTaskString,
                    runTime
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> infoInterface.update());
    }

    @Override
    public void onValueChanged(VarbitEvent varbitEvent) {
        int varbit = varbitEvent.getVarbit().getId();
        int oldValue = varbitEvent.getOldValue();
        int newValue = varbitEvent.getNewValue();
        switch (varbit) {
            case 25: // Stamina
                if (newValue == 1) {
                    staminaActive = true;
                    staminaTimer.reset();
                } else if (newValue == 0) {
                    staminaActive = false;
                }
        }
    }

    @Override
    public void onValueChanged(VarpEvent varpEvent) {
        int varp = varpEvent.getVarp().getIndex();
        int oldValue = varpEvent.getOldValue();
        int newValue = varpEvent.getNewValue();
        int diff = newValue - oldValue;
        // essence pouches
        if (varp == 261) {
            switch (diff) {
                case 1:
                    Small_Pouch.fillPouch();
                    System.out.println("Filled small pouch");
                    break;
                case 2:
                    Medium_Pouch.fillPouch();
                    System.out.println("Filled medium pouch");
                    break;
                case 4:
                    Large_Pouch.fillPouch();
                    System.out.println("Filled large pouch");
                    break;
                case 8:
                    Giant_Pouch.fillPouch();
                    System.out.println("Filled Giant pouch");
                    break;
            }
        }
    }
}
