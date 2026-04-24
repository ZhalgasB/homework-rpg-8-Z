package com.narxoz.rpg.combatant;

import com.narxoz.rpg.state.HeroState;

/**
 * Represents a player-controlled hero participating in the tower climb.
 *
 * Students: you may extend this class as needed for your implementation.
 * You will need to add a HeroState field and related methods.
 */
public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;



    private HeroState state;

    public Hero(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public void setState(HeroState state) {
        System.out.println(name + "state: " + state.getName());

        this.state = state;
    }



    public HeroState getState() {
        return state;
    }

    public int getModifiedAttack(){
        return state.modifyOutgoingDamage(attackPower);
    }

    public void applyIncDamage(int rawDmg){
        int finalDmg = state.modifyIncomingDamage(Math.max(0,rawDmg-defense));
        takeDamage(finalDmg);
        System.out.println(name + " take " + finalDmg + "DMG.HP: " + hp );
    }

    public String getName()        { return name; }
    public int getHp()             { return hp; }
    public int getMaxHp()          { return maxHp; }
    public int getAttackPower()    { return attackPower; }
    public int getDefense()        { return defense; }
    public boolean isAlive()       { return hp > 0; }

    /**
     * Reduces this hero's HP by the given amount, clamped to zero.
     *
     * @param amount the damage to apply; must be non-negative
     */
    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    /**
     * Restores this hero's HP by the given amount, clamped to maxHp.
     *
     * @param amount the HP to restore; must be non-negative
     */
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }
}
