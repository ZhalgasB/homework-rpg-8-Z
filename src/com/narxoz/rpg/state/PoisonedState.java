package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState{

    private int duration = 3;

    @Override
    public String getName() {
        return "Poisoned";
    }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return (int)(basePower * 0.8);
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return rawDamage;
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println(hero.getName() + " Poisoned");
    }

    @Override
    public void onTurnEnd(Hero hero) {
        duration--;

        if (duration <=- 0){
            System.out.println(hero.getName() + " Not poisoned anymore");
            hero.setState(new NormalState());
        }
    }

    @Override
    public boolean canAct() {
        return true;
    }
}
