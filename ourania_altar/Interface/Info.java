package com.stixx.bots.ourania_altar.Interface;

import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;

import java.util.concurrent.TimeUnit;

/*
*
* --- Info Class ---
* This class is used to transfer information between
* the two threads we have in this bot. The GUI and bot threads.
*
* Inside exampleflaxpicker.java you will see that in updateInfo()
* we create a new Info class (this class) and assign its constructor
* the appropriate values. You do this so you can pass that newly gathered
* information to the GUI thread, in a thread-safe manor by Platform.runLater().
*
* (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), rcExperienceGained),  //   -   -   -   -   -   -   -   // xp per hour
                    (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), moneyGained),
 */
public class Info {

    public int rcXP, money, gpPh, rcLevels, magicXP, magicLevels, rcPh, magicPh;
    public String runTime, currentTask, magicInfo, rcInfo, gpInfo;


    public Info(int rcXP, int magicXP, int rcLevels, int magicLevels, int money, StopWatch stopWatch, String currentTask) {
        this.rcXP = rcXP;
        this.magicXP = magicXP;
        this.rcLevels = rcLevels;
        this.magicLevels = magicLevels;
        this.money = money;
        this.runTime = stopWatch.getRuntimeAsString();
        this.currentTask = currentTask;

        this.rcPh = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), rcXP);
        this.magicPh = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), magicXP);
        this.gpPh = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), money);

    }


}