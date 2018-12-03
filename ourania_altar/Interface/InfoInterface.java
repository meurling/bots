package com.stixx.bots.ourania_altar.Interface;

import com.runemate.game.api.hybrid.util.Resources;
import com.stixx.bots.ourania_altar.OuraniaAltar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class InfoInterface extends GridPane implements Initializable {

    private OuraniaAltar bot;

    @FXML
    Label FlaxPH_L, FlaxCount_L, Runtime_L, CurrentTask_L, RcXpPh_L, RcXp_L, GpPh_L,Money_L;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVisible(true);
    }

    public InfoInterface(OuraniaAltar bot) {
        this.bot = bot;

        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();

        // Input your InfoUI FXML file location here.
        // NOTE: DO NOT FORGET TO ADD IT TO MANIFEST AS A RESOURCE
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/stixx/bots/ourania_altar/Interface/InfoInterface.fxml"));


        // Set this class as root AND Controller for the Java FX GUI
        loader.setController(this);

        // NOTE: By setting the root to (this) you must change your .fxml to reflect fx:root
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // This method will update the text that is presented to the end user
    public void update() {
        try {
            Info i = bot.info;

            Money_L.textProperty().set("" + i.money);
            GpPh_L.textProperty().set("" + i.gpPh);
            RcXpPh_L.textProperty().set("" + i.rcXpPh);
            RcXp_L.textProperty().set("" + i.rcXP);
            Runtime_L.textProperty().set("" + i.runTime);
            CurrentTask_L.textProperty().set(i.currentTask);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
