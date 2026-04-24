package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;

import java.util.List;

public class TowerRunner {
    private final List<TowerFloor> floors;

    public TowerRunner(List<TowerFloor> floors) {
        this.floors = floors;
    }

    public TowerRunResult run(List<Hero> party){
        int cleared = 0;

        for (TowerFloor floor : floors){
            boolean anuAlive = false;

            for (Hero h : party){
                if (h.isAlive()){
                    anuAlive = true;
                    break;
                }
            }

            if (!anuAlive){
                System.out.println("All heroes are defeated");
                break;
            }

            FloorResult result = floor.explore(party);

            if (result.isCleared()){
                cleared++;
            }else {
                System.out.println("Party could not complete the floor " + result.getSummary());
                break;
            }

            for (Hero h : party){
                if (h.isAlive()){
                    h.getState().onTurnEnd(h);
                }
            }
        }

        int aliveC = 0;

        for (Hero h : party){
            if (h.isAlive()){
                aliveC++;
            }
        }

        boolean reachTop = (cleared == floors.size());
        return new TowerRunResult(cleared,aliveC,reachTop);

    }
}
