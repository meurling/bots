package com.stixx.bots.ourania_altar.Interface;

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
 */
public class Info {

    public int rcXP, money, gpPh, rcXpPh;
    public String runTime, currentTask;

    public Info() {
        this.rcXpPh = 0;
        this.rcXP = 0;
        this.gpPh = 0;
        this.money = 0;
        this.runTime = "";
        this.currentTask = "";
    }

    public Info(int rcPh, int gpPh, int rcXp, int money, String runTime, String currentTask) {
        this.rcXpPh = rcPh;
        this.gpPh = gpPh;
        this.rcXP = rcXp;
        this.money = money;
        this.runTime = runTime;
        this.currentTask = currentTask;
    }

}