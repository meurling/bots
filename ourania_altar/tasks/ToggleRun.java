package com.stixx.bots.ourania_altar.tasks;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.task.Task;

public class ToggleRun extends Task {
    @Override
    public void execute() {
        Traversal.toggleRun();
    }

    @Override
    public boolean validate() {
        return !Traversal.isRunEnabled() && Traversal.getRunEnergy() >= 20;
    }
}
