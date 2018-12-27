package com.stixx.bots.zmi_runecrafter.player_in_bank;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.stixx.bots.zmi_runecrafter.player_in_bank.MustDrinkStamina;
import com.stixx.bots.zmi_runecrafter.player_in_bank.bank_is_closed.consumeables.CanEatFood;

// import path.to.your.CanEatFood
// import path.to.your.MustDrinkStamina

/**
 * NOTES:
 * 
 */
public class MustEatFood extends BranchTask {

    private CanEatFood caneatfood = new CanEatFood();
    private MustDrinkStamina mustdrinkstamina = new MustDrinkStamina();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return mustdrinkstamina;
    }

    @Override
    public TreeTask successTask() {
        return caneatfood;
    }
}
