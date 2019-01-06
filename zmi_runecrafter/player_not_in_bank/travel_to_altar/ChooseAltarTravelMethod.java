package com.stixx.bots.zmi_runecrafter.player_not_in_bank.travel_to_altar;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.stixx.bots.zmi_runecrafter.ZMI;

import java.util.Random;

/**
 * NOTES:
 * 
 */
public class ChooseAltarTravelMethod extends LeafTask {
    private ZMI bot;
    public ChooseAltarTravelMethod(ZMI bot) {
        this.bot =bot;
    }



    @Override
    public void execute() {
        System.out.println("Choose travel method");
        if (bot.SETTING_USE_FAST_ROUTE) {
            useFastRoute();
        } else {
            useSlowRoute();
        }
    }

    private void useFastRoute() {
        Random random = new Random();
        if (random.nextInt(10) + 1 > 7) {
            LocatableEntityQueryResults<Player> results = Players.newQuery().moving().reachable().within(bot.FIRST_AREA).results();
            if (results.size() > 0) {
                Player followPlayer = results.nearest();
                if (followPlayer != null) {
                    Camera.turnTo(bot.FIRST_AREA);
                    if (followPlayer.interact("Follow")) {
                        bot.followingPlayer = true;
                        bot.currentTaskString = "Following player to altar";
                        Execution.delay(200,400);
                    }
                } else {
                    TravelToAltar travelToAltar = new TravelToAltar(bot);
                    travelToAltar.execute();
                }
            }
        } else {
            TravelToAltar travelToAltar = new TravelToAltar(bot);
            travelToAltar.execute();
        }

    }

    private void useSlowRoute(){
        bot.currentTaskString = "Manually walking to Altar (Slow route)";

        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bot.SHORTCUT_AREA);
        if (path!= null) {
            path.step();
        }
    }
}
