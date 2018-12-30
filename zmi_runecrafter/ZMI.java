package com.stixx.bots.zmi_runecrafter;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.script.framework.listeners.VarpListener;
import com.runemate.game.api.script.framework.listeners.events.VarpEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.Interface.InfoInterface;
import com.stixx.bots.zmi_runecrafter.Interface.UserInterface;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

import java.util.List;

public class ZMI extends TreeBot implements EmbeddableUI {

    //---------------BRANCHES---------------//
    //---------------BRANCHES---------------//


    //---------------SETTINGS---------------//
        public List<EssencePouch> SETTING_ESSENCE_POUCHES;
        public List<String> SETTING_STAFF_RUNES;
        public List<String> SETTING_INVENTORY_RUNES;
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
    //---------------RUN_TIME---------------//

    //---------------GUIs---------------//
        public boolean waitingForGUI = true;
        public SimpleObjectProperty<Node> botInterfaceProperty;
        private InfoInterface infoInterface;
        public UserInterface userInterface; // configs
    //---------------GUIs---------------//

    //---------------VARIABLES---------------//
        public Player player;
    //---------------VARIABLES---------------//


    @Override
    public void onStart(String... arguments) {
        super.onStart(arguments);
        currentTaskString = "Starting up...";
        botInterfaceProperty = null;
        setEmbeddableUI(this);
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
        botInterfaceProperty.set(infoInterface);
    }

    public void updateInfo() {

    }
}
