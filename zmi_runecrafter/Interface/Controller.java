package com.stixx.bots.zmi_runecrafter.Interface;

import com.stixx.bots.zmi_runecrafter.ZMI;
import com.stixx.bots.zmi_runecrafter.custom_objects.EssencePouch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private ZMI bot;

    public Controller(ZMI bot) {
        this.bot = bot;

    }

    @FXML
    private CheckBox Small_Pouch, Medium_Pouch, Large_Pouch, Giant_Pouch, Staff_Air, Staff_Earth, Inventory_Air, Inventory_Earth, Inventory_Law, Inventory_Astral, Inventory_Cosmic, Inventory_Fire, Inventory_Dust;

    @FXML
    private ComboBox Stamina_Box,Route_Box, Teleport_Box, Food_Box, RunePouch_Box;

    @FXML
    Button Start_Button;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("am1");

        Stamina_Box.getItems().addAll("Yes", "No");
        System.out.println("am2");

        Route_Box.getItems().addAll("Fast route", "Slow route");
        System.out.println("am3");

        Teleport_Box.getItems().addAll("Yes", "No");
        System.out.println("am4");

        Food_Box.getItems().addAll("Monkfish", "Trout", "Salmon", "Lobster", "Tuna");
        System.out.println("am5");

        RunePouch_Box.getItems().addAll("Yes", "No");
        System.out.println("am6");


        Start_Button.setOnAction(Start_Button_Click());
        Stamina_Box.setOnAction(Should_Start_Button_Activate());
        Route_Box.setOnAction(Should_Start_Button_Activate());
        Teleport_Box.setOnAction(Should_Start_Button_Activate());
        Food_Box.setOnAction(Should_Start_Button_Activate());
        RunePouch_Box.setOnAction(Should_Start_Button_Activate());

    }

    public EventHandler<ActionEvent> Should_Start_Button_Activate() {
        return event -> {
            if (Stamina_Box.getSelectionModel().getSelectedItem() != null && Teleport_Box.getSelectionModel().getSelectedItem() != null && Route_Box.getSelectionModel().getSelectedItem() != null && Food_Box.getSelectionModel().getSelectedItem() != null && RunePouch_Box.getSelectionModel().getSelectedItem() != null) {
                Start_Button.setDisable(false);
            } else {
                Start_Button.setDisable(true);
            }
        };
    }

    public EventHandler<ActionEvent> Start_Button_Click() {
        return event -> {
            try {
                // init all settings

                setEssencePouches();
                setInventoryRunes();
                setStaffRunes();

                // food
                bot.SETTING_FOOD = Food_Box.getSelectionModel().getSelectedItem().toString();
                // route
                if (Route_Box.getSelectionModel().getSelectedItem().toString() == "Fast route") {
                    bot.SETTING_USE_FAST_ROUTE = true;
                } else {
                    bot.SETTING_USE_FAST_ROUTE = false;
                }
                // teleport
                if (Teleport_Box.getSelectionModel().getSelectedItem().toString() == "Yes") {
                    bot.SETTING_USE_TELEPORT = true;
                } else {
                    bot.SETTING_USE_TELEPORT = false;
                }
                // stamina
                if (Stamina_Box.getSelectionModel().getSelectedItem().toString() == "Yes") {
                    bot.SETTING_USE_STAMINA = true;
                } else {
                    bot.SETTING_USE_STAMINA = false;
                }

                if (RunePouch_Box.getSelectionModel().getSelectedItem().toString() == "Yes") {
                    bot.SETTING_USE_RUNEPOUCH = true;
                } else {
                    bot.SETTING_USE_RUNEPOUCH = false;
                }

                bot.waitingForGUI = false;

                Platform.runLater(() -> bot.setToInfoProperty());

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private void setInventoryRunes() {
        if (Inventory_Air.isSelected()) {
            bot.SETTING_INVENTORY_RUNES.add("Air rune");
        }
        if (Inventory_Earth.isSelected()) {
            bot.SETTING_INVENTORY_RUNES.add("Earth rune");
        }
        if (Inventory_Law.isSelected()) {
            bot.SETTING_INVENTORY_RUNES.add("Law rune");
        }
        if (Inventory_Cosmic.isSelected()) {
            bot.SETTING_INVENTORY_RUNES.add("Cosmic rune");
        }
        if (Inventory_Astral.isSelected()) {
            bot.SETTING_INVENTORY_RUNES.add("Astral rune");
        }
        if (Inventory_Fire.isSelected()) {
            bot.SETTING_INVENTORY_RUNES.add("Fire rune");
        }
        if (Inventory_Dust.isSelected()) {
            bot.SETTING_INVENTORY_RUNES.add("Dust rune");
        }
    }

    private void setStaffRunes() {
        if (Staff_Air.isSelected()) {
            bot.SETTING_STAFF_RUNES.add("Air rune");
        }
        if (Staff_Earth.isSelected()) {
            bot.SETTING_STAFF_RUNES.add("Earth rune");
        }
    }

    private void setEssencePouches() {
        if (Small_Pouch.isSelected()) {
            bot.SETTING_ESSENCE_POUCHES.add(new EssencePouch("Small pouch", 3));
        }
        if (Small_Pouch.isSelected()) {
            bot.SETTING_ESSENCE_POUCHES.add(new EssencePouch("Medium pouch", 6));
        }
        if (Small_Pouch.isSelected()) {
            bot.SETTING_ESSENCE_POUCHES.add(new EssencePouch("Large pouch", 9));
        }
        if (Small_Pouch.isSelected()) {
            bot.SETTING_ESSENCE_POUCHES.add(new EssencePouch("Giant pouch", 12));
        }
    }
}
