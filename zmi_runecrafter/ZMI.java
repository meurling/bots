package com.stixx.bots.zmi_runecrafter;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.listeners.VarbitListener;
import com.runemate.game.api.script.framework.listeners.VarpListener;
import com.runemate.game.api.script.framework.listeners.events.VarbitEvent;
import com.runemate.game.api.script.framework.listeners.events.VarpEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.Interface.InfoInterface;
import com.stixx.bots.zmi_runecrafter.Interface.UserInterface;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class ZMI extends TreeBot implements EmbeddableUI, VarbitListener {

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
        public int levelsGained;
        public int magicLevelsGained;
        public int rcExperienceGained;
        public int moneyGained;
        public String currentTaskString;
        public int magicExperienceGained;
        public Helper helper;
    //---------------RUN_TIME---------------//

    //---------------GUIs---------------//
        public boolean waitingForGUI = true;
        public SimpleObjectProperty<Node> botInterfaceProperty;
        private InfoInterface infoInterface;
        public UserInterface userInterface; // configs
    //---------------GUIs---------------//

    //---------------VARIABLES---------------//
        public Player player;
        public StopWatch staminaTimer = new StopWatch();
        public boolean staminaActive = false;
        public StopWatch runTime = new StopWatch();
    //---------------VARIABLES---------------//

    //---------------AREAS---------------//
        public Area BANK_AREA = new Area.Rectangular(new Coordinate(3009, 5624, 0), new Coordinate(3021, 5630, 0));
    //---------------AREAS---------------//

    @Override
    public void onStart(String... arguments) {
        super.onStart(arguments);
        currentTaskString = "Starting up...";
        botInterfaceProperty = null;
        helper = new Helper(this);
        setEmbeddableUI(this);
        runTime.start();
    }
    /*
    @Override
    public void onValueChanged(VarpEvent varpEvent) {

    }
*/
    @Override
    public TreeTask createRootTask() {
        return new Root(this);
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
}
