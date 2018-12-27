package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class Loop extends Task {
    @Override
    public void execute() {

    }

    @Override
    public boolean validate() {
        if (!OuraniaAltar.guiWait) {
            OuraniaAltar.updateInfo();
        }
        return false;
    }
}
