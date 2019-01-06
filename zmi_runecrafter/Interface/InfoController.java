package com.stixx.bots.zmi_runecrafter.Interface;

import com.stixx.bots.zmi_runecrafter.ZMI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {

    private ZMI bot;

    @FXML
    Label ExperienceGained, ExperiencePerHour, RunTime;

    public InfoController(ZMI bot) {
        this.bot = bot;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void update() {
        try {
            Info i = bot.info;

            ExperiencePerHour.textProperty().set("" + i.experiencePerHour);
            RunTime.textProperty().set("" + i.runTime);
            ExperienceGained.textProperty().set("" + i.experienceGained + " (" + i.levelsGained + ")");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
