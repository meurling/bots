package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.task.Task;
import com.stixx.bots.ourania_altar.OuraniaAltar;

public class ToggleRun extends Task {
    @Override
    public void execute() {
        Traversal.toggleRun();
    }

    @Override
    public boolean validate() {
        return OuraniaAltar.shouldTasksPause() && !Traversal.isRunEnabled() && Traversal.getRunEnergy() >= 20;
    }
}
