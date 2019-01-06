package com.stixx.bots.zmi_runecrafter.Interface;

import com.runemate.game.api.hybrid.util.Resources;
import com.stixx.bots.zmi_runecrafter.ZMI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class InfoInterface extends GridPane implements Initializable {

    private ZMI bot;

    @FXML
    public Text ExperienceGained, ExperiencePerHour, RunTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVisible(true);
    }

    public InfoInterface(ZMI bot) {
        this.bot = bot;

        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();

        // Input your InfoUI FXML file location here.
        // NOTE: DO NOT FORGET TO ADD IT TO MANIFEST AS A RESOURCE
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/stixx/bots/zmi_runecrafter/Interface/InfoInterface.fxml"));


        // Set this class as root AND Controller for the Java FX GUI
        loader.setController(this);

        // NOTE: By setting the root to (this) you must change your .fxml to reflect fx:root
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println("Error Infoi nterface");
            e.printStackTrace();
        }
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
