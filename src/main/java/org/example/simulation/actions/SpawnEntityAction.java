package org.example.simulation.actions;

import org.example.simulation.entities.Entity;
import org.example.simulation.map.Coordinates;
import org.example.simulation.map.WorldMap;
import org.example.simulation.utils.EntitiesFactory;

import java.util.Map;

public class SpawnEntityAction implements Action {

    public static final String EXCEPTION_MESSAGE ="Please check the compliance of the entity spawn " +
            "parameters with the provided specification!";
    private final EntitiesFactory spawnData;

    public SpawnEntityAction(EntitiesFactory spawnData) {
        this.spawnData = spawnData;

    }

    public void execute(WorldMap worldMap) {
        spawnEntities(worldMap);
    }

    private void spawnEntities(WorldMap worldMap) {
        Map<Entity, Integer> spawns = spawnData.getSpawns(worldMap);
        try {
            if(spawnsIsValid(spawns, worldMap)) {
                for (java.util.Map.Entry<Entity, Integer> e : spawns.entrySet()) {
                    if (e.getValue()==0){
                        continue;
                    }
                    for (int i = 0; i < e.getValue()+1; i++) {
                        Coordinates randomCoordinates = Coordinates.getRandomAvailableCoordinate(worldMap);
                        worldMap.set(randomCoordinates, e.getKey());
                    }
                }
            }else
                throw new IllegalConstantArgumentException();
        }catch (IllegalConstantArgumentException e){
            System.out.println(EXCEPTION_MESSAGE);
        }
    }


    private boolean spawnsIsValid(Map<Entity, Integer> spawns, WorldMap worldMap){
        if(!spawns.isEmpty()){
            int sumOfConstants = spawns.values().stream().mapToInt(Integer::intValue).sum();
            return sumOfConstants <= worldMap.mapSize;
        }
        return false;
    }
}
