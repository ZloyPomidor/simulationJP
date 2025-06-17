package org.example.simulation.entities.utils;

import org.example.simulation.entities.Entity;
import org.example.simulation.entities.creatures.Herbivore;
import org.example.simulation.entities.creatures.Predator;
import org.example.simulation.entities.staticEntities.Grass;
import org.example.simulation.entities.staticEntities.Rock;
import org.example.simulation.entities.staticEntities.Tree;
import org.example.simulation.map.WorldMap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntitiesFactory {
    public final double predator; //<|
    public final double herbivore;// |
    public final double grass;//     |
    public final double tree;//      |
    public final double rock;//    < \___ <=1.00
    public final List<Entity> entities = List.of(new Herbivore(), new Predator(),
            new Grass(), new Rock(), new Tree());

    public EntitiesFactory() {
        this.predator = 0.02;
        this.herbivore = 0.09;
        this.grass = 0.10;
        this.tree =  0.20;
        this.rock = 0.20;
    }

    public EntitiesFactory(double predator, double herbivore, double grass, double tree, double rock) {
        this.predator = predator;
        this.herbivore = herbivore;
        this.grass = grass;
        this.tree = tree;
        this.rock = rock;

    }

    public int getTargetSpawnValue(Entity entity, WorldMap worldMap){
        try {
            double spawnCounter = 0.0;
            for (String s: getSpawnValues()){
                String entitySimpleName = entity.getClass().getSimpleName();

                if(isTargetSpawnValue(entitySimpleName, s)){
                    spawnCounter = getSpawnCounter(s);
                }
            }
            return (int) (spawnCounter * worldMap.mapSize);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Entity, Integer> getSpawns(WorldMap worldMap) {
        Map<Entity, Integer> spawns = new HashMap<>();
        for (Entity entity: entities) {
            int spawnValue = getTargetSpawnValue(entity, worldMap);
            spawns.put(entity,spawnValue);
        }
        return spawns;
    }

    private double getSpawnCounter(String constantName) throws NoSuchFieldException, IllegalAccessException {
        Field field = EntitiesFactory.class.getField(constantName);
        return field.getDouble(this);
    }

    private boolean isTargetSpawnValue(String entityClassSimpleName, String spawnValueNameParts){
        return entityClassSimpleName.equalsIgnoreCase(spawnValueNameParts);

    }

    public List<String> getSpawnValues(){
        return Arrays.stream(EntitiesFactory.class.getDeclaredFields()).
                filter(field-> Modifier.isFinal(field.getModifiers())).
                map(Field::getName).collect(Collectors.toList());
    }
}