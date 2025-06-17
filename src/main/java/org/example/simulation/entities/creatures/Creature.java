package org.example.simulation.entities.creatures;

import org.example.simulation.map.Coordinates;
import org.example.simulation.entities.Entity;
import org.example.simulation.map.WorldMap;
import org.example.simulation.pathFinder.PathFinder;


public abstract class Creature extends Entity {

    private final int maxHpValue;
    private final Class<? extends Entity> target;
    private final int speed;
    private int hp;

    public Creature(int hp, int maxHpValue, Class<? extends Entity> target, int speed) {
        this.hp = hp;
        this.maxHpValue = maxHpValue;
        this.target = target;
        this.speed = speed;
    }

    public abstract void attack(WorldMap worldMap, Coordinates to);


    public void makeMove(WorldMap worldMap, Coordinates currentCoordinate){
            PathFinder pathFinder = new PathFinder(worldMap);
            Coordinates nextCoordinate = pathFinder.getNextStep(currentCoordinate);
            moveEntity(worldMap, currentCoordinate, nextCoordinate);

    }

    public void moveEntity(WorldMap worldMap,Coordinates from, Coordinates to) {
        Creature creatureFrom = (Creature) worldMap.getEntity(from);
        if (!worldMap.coordinatesIsEmpty(to)) {
            interactionOnEntity(worldMap,to);
        } else {
            worldMap.removeEntity(from);
            worldMap.set(to, creatureFrom);
        }
    }

    public void eit() {
        if(getHp()< maxHpValue){
            hpApp();
        }
    }

    public boolean isTarget(Entity entityTo) {
        return target.equals(entityTo.getClass());
    }

    public void hpApp(){
        this.hp++;
    }

    public void hpDown(int attackPower){
        this.hp = hp - attackPower;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public Class<? extends Entity> getTarget() {return target;}

    private void interactionOnEntity(WorldMap worldMap, Coordinates to) {
        Entity entityTo = worldMap.getEntity(to);

        if (isTarget(entityTo)) {
            attack(worldMap, to);
        }
    }


}