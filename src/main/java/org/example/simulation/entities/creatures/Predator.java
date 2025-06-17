package org.example.simulation.entities.creatures;

import org.example.simulation.map.Coordinates;
import org.example.simulation.map.WorldMap;
import org.example.simulation.entities.Entity;

public class Predator extends Creature{
    private static final int DEFAULT_HP_VALUE = 5;
    private static final int HP_MAX_VALUE = 5;
    private static final int DEFAULT_SPEED = 1;
    private static final Class<? extends Entity> DEFAULT_GOAL = Herbivore.class;
    private static final int ATTACK_POWER = 2;


    public Predator(){
        super(DEFAULT_HP_VALUE,
                HP_MAX_VALUE,
                DEFAULT_GOAL,
                DEFAULT_SPEED);
    }


    @Override
    public void attack(WorldMap worldMap, Coordinates to) {
        Creature creatureTo = (Creature) worldMap.getEntity(to);
        if (creatureTo.getHp()>ATTACK_POWER){
            creatureTo.hpDown(ATTACK_POWER);
            worldMap.set(to,creatureTo);
        }else{
            eit();
            worldMap.removeEntity(to);
        }

    }
}
