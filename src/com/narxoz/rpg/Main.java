package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.state.*;
import com.narxoz.rpg.tower.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("TOWER SIMULATOR ");

        Hero warrior = new Hero("Lelouch", 130, 25, 8);
        Hero mage = new Hero("CC", 85, 45, 4);

        mage.setState(new PoisonedState());

        List<Hero> party = Arrays.asList(warrior, mage);

        List<TowerFloor> tower = Arrays.asList(
                new RestFloor(),
                new CombatFloor(),
                new RestFloor(),
                new BossFloor()
        );

        TowerRunner runner = new TowerRunner(tower);
        TowerRunResult result = runner.run(party);

        System.out.println("FINAL ASCENSION REPORT");
        System.out.println("Floors Cleared: " + result.getFloorsCleared() + "/" + tower.size());
        System.out.println("Survivors: " + result.getHeroesSurviving());

        String won;
        if (result.isReachedTop()){
            won = "VICTORY";
        }else won = "DEFEAT";

        System.out.println("Status: " + won);

    }
}