package org.example.simulation.actions;

import org.example.simulation.entities.Entity;
import org.example.simulation.entities.creatures.Herbivore;
import org.example.simulation.entities.staticEntities.Grass;
import org.example.simulation.map.Coordinates;
import org.example.simulation.map.WorldMap;
import org.example.simulation.entities.Utils.EntitiesFactory;

import java.util.Map;

public class AddEntityAction implements Action {

    private final EntitiesFactory spawnData;

    public AddEntityAction( EntitiesFactory spawnData) {
        this.spawnData = spawnData;
    }

        @Override
    public void execute(WorldMap worldMap) {
        spawnTarget(worldMap);
    }

    private void spawnTarget(WorldMap worldMap) {
        Map<Entity, Integer> spawns = spawnData.getSpawns(worldMap);
        for (Map.Entry<Entity, Integer> e : spawns.entrySet()) {
            if(thatEntityDeficit(e,worldMap)){

                if(isEntityAddable(e.getKey(),worldMap)){
                    addEntity(e.getKey(), worldMap);
                }
            }
        }
    }

    private boolean thatEntityDeficit(Map.Entry<Entity, Integer> e, WorldMap worldMap ) {
        return worldMap.getCoordinatesByEntityType(e.getKey().getClass()).size() < e.getValue();

    }

    private void addEntity(Entity entity, WorldMap worldMap) {
        int targetConstant = spawnData.getTargetSpawnValue(entity, worldMap);
        int deficitEntities = Math.abs(targetConstant-getQuantityEntitiesOnMap(entity,worldMap));
        for (int i = 0; i<=deficitEntities; i++){
            Coordinates randomCoordinates = Coordinates.getRandomAvailableCoordinate(worldMap);
            worldMap.set(randomCoordinates, entity);
        }
    }

    private boolean isEntityAddable(Entity entity,WorldMap worldMap){
        if (isEntityAvailableForAdd(entity)){
            int targetSpawnValue = spawnData.getTargetSpawnValue(entity, worldMap);
            return getQuantityEntitiesOnMap(entity,worldMap) < targetSpawnValue;
        }
        return false;
    }

    private int getQuantityEntitiesOnMap(Entity entity,WorldMap worldMap){
        return worldMap.getCoordinatesByEntityType(entity.getClass()).size();
    }

    private boolean isEntityAvailableForAdd(Entity entityForAdd){
        return entityForAdd instanceof Herbivore || entityForAdd instanceof Grass;
    }

}
