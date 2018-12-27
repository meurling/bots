package com.stixx.bots.ourania_altar.Interface;

import com.stixx.bots.ourania_altar.OuraniaAltar;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Java FX Controller for the FlaxFXGui class
 *
 * The controller class is where the logic and implementation of GUI events go.
 *      Ex. If you press on a Start button in a typical program, you'd expect the program to actually start.
 *          That event handling would be found here.
 *          NOTE:   You can assign a single class to be the FXML Loader And Controller.
 *                  To do this, just set your FXML's loader to .setController(this) in appropriate class.
 */
public class Controller implements Initializable {

    private OuraniaAltar bot;

    @FXML
    private ComboBox EssencePouch_Box;

    @FXML
    private ComboBox RunePouch_Box;

    @FXML
    private Button Start_BT;

    private final String option0 = "No pouch", option1 = "Medium, small", option2 ="Large, medium, small", option3 = "Giant, large, medium, small";


    public Controller() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add Locations to the combo box
        EssencePouch_Box.getItems().addAll("No pouch", "Medium, small", "Large, medium, small", "Giant, large, medium, small");

        RunePouch_Box.getItems().addAll("Yes", "No");
        // If the Start Button is pressed, handle that even in the getStart_BTAction method
        Start_BT.setOnAction(getStart_BTAction());

        // Set the event for Location_ComboBo
        EssencePouch_Box.setOnAction(setEssencePouchSetting());

        RunePouch_Box.setOnAction(setEssencePouchSetting());
    }

    public EventHandler<ActionEvent> getStart_BTAction() {
        return event -> {
            try {
                // Initialize all variables in your bot

                switch (EssencePouch_Box.getSelectionModel().getSelectedItem().toString()) {
                    case "No pouch":
                        OuraniaAltar.OPTION_ESSENCEPOUCH = 0;
                        break;
                    case "Medium, small":
                        OuraniaAltar.OPTION_ESSENCEPOUCH = 1;
                        break;
                    case "Large, medium, small":
                        System.out.println("gaueiofn");
                        OuraniaAltar.OPTION_ESSENCEPOUCH = 2;
                        break;
                    case "Giant, large, medium, small":
                        System.out.println("aaaaaaaaaaaaaaaaa");

                        OuraniaAltar.OPTION_ESSENCEPOUCH = 3;
                        break;
                }

                switch (RunePouch_Box.getSelectionModel().getSelectedItem().toString()) {
                    case "Yes":
                        OuraniaAltar.OPTION_PAYMENT_RUNE = "Rune pouch";
                    case "No":
                        OuraniaAltar.OPTION_PAYMENT_RUNE = "";
                }

                OuraniaAltar.guiWait = false;


                // Set the EmbeddableUI property to reflect your Info GUI
                Platform.runLater(() -> OuraniaAltar.setToInfoProperty() );

            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }

    public EventHandler<ActionEvent> setEssencePouchSetting() {
        return event -> {
            // If a value is assigned to the Combo Box, enable tot he Start Button.
            if (EssencePouch_Box.getSelectionModel().getSelectedItem() != null && RunePouch_Box.getSelectionModel().getSelectedItem() != null) {
                System.out.println("Selected: " + EssencePouch_Box.getSelectionModel().getSelectedItem().toString() + ", " + RunePouch_Box.getSelectionModel().getSelectedItem());
                Start_BT.setDisable(false);
            }
            else
                Start_BT.setDisable(true);
        };
    }
}