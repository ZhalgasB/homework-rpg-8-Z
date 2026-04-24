package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;

import java.util.List;

public class RestFloor extends TowerFloor{

    @Override
    protected void announce(){
        System.out.println("Found a peace place");
    }

    @Override
    protected void setup(List<Hero> party) {
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        for (Hero h : party){
            h.heal(20);
        }

        return new FloorResult(true,0,"Heroes have Healed");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {

    }

    @Override
    protected String getFloorName() {
        return "PeaceFull Floor";
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result){
        return false;
    }
}
