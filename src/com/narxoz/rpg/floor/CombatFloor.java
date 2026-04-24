package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;

import java.util.List;

public class CombatFloor extends TowerFloor{
    private Monster enemy;

    @Override
    protected void setup(List<Hero> party) {
        enemy = new Monster("Goblin",50,15);
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int totalBmg = 0;

        for (Hero hero : party){
            if (hero.isAlive() && hero.getState().canAct()){
                int damage = hero.getModifiedAttack();
                enemy.takeDamage(damage);
                System.out.println(hero.getName() + " attacks " + enemy.getName());

            }
            if (enemy.isAlive()){
                enemy.attack(hero);
                totalBmg += 10;
            }
        }
        return new FloorResult(enemy.getHp() <= 0,totalBmg,"Fight is over");


    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("Sword in garbage");
    }

    @Override
    protected String getFloorName() {
        return "Goblins Floor";
    }
}
