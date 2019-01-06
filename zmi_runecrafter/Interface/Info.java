package com.stixx.bots.zmi_runecrafter.Interface;

import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;

import java.util.concurrent.TimeUnit;

public class Info {

    public int experiencePerHour, experienceGained, levelsGained;
    public String currentTask, runTime;
    public StopWatch stopWatch;

    public Info(int experienceGained, int levelsGained, String currentTask, StopWatch stopWatch) {
        this.experienceGained = experienceGained;
        this.levelsGained = levelsGained;
        this.currentTask = currentTask;
        this.stopWatch = stopWatch;

        this.experiencePerHour = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), experienceGained);
        this.runTime = stopWatch.getRuntimeAsString();
    }
}
