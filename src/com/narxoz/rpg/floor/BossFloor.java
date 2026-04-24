package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.*;
import java.util.List;

public class BossFloor extends TowerFloor {
    private Monster boss;

    protected String getFloorName() {
        return "The Shadow Throne";
    }

    @Override
    protected void announce() {
        System.out.println("The Shadow Lord awaits you");
    }

    protected void setup(List<Hero> party) {
        boss = new Monster("Shadow Lord", 120, 22);
    }

    protected FloorResult resolveChallenge(List<Hero> party) {
        while (boss.isAlive()) {
            boolean actionsTaken = false;
            for (Hero h : party) {
                if (h.isAlive()) {
                    h.getState().onTurnStart(h);

                    if (h.getState().canAct()) {
                        int damage = h.getModifiedAttack();
                        boss.takeDamage(damage);
                        System.out.println(h.getName() + " attacks " + boss.getName());
                        actionsTaken = true;
                    } else {
                        System.out.println(h.getName() + "skips the turn");
                    }
                    if (boss.isAlive() && h.isAlive()) {
                        boss.attack(h);
                    }
                }
            }
            boolean partyDead = true;
            for (Hero h : party) {
                if (h.isAlive()) {
                    partyDead = false;
                    break;
                }
            }

            if (partyDead) return new FloorResult(false, 0, "The party was defeted");
            if (!actionsTaken && !boss.isAlive()) break;
            if (!boss.isAlive()) break;
        }
        return new FloorResult(true, 0, "The Shadow Lord was Smashed like a burger");
    }

    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("The Tower's core is yours");
    }
}