package org.example.simulation.entities.creatures;

import org.example.simulation.map.Coordinates;
import org.example.simulation.entities.Entity;
import org.example.simulation.entities.staticEntities.Grass;
import org.example.simulation.map.WorldMap;

public class Herbivore extends Creature{
    private static final int DEFAULT_HP_VALUE = 3;
    private static final int HP_MAX_VALUE = 3;
    private static final int DEFAULT_SPEED = 1;
    private static final Class<? extends Entity> DEFAULT_TARGET = Grass.class;

    public Herbivore() {
        super(DEFAULT_HP_VALUE,
                HP_MAX_VALUE,
                DEFAULT_TARGET, // волк не должен держать в кармене зайца
                DEFAULT_SPEED);
    }

    @Override
    public void attack(WorldMap worldMap, Coordinates to) {
      worldMap.removeEntity(to);
      eit();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
