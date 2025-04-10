package org.example.simulation.map;

import org.example.simulation.entities.Entity;

import java.util.*;

public class WorldMap {

    public static final int MIN_COORDINATES_OF_THE_WORLD = 0;
    public final int column;
    public final int row;
    public final int mapSize;
    private final Map<Coordinates, Entity> entities;

    public WorldMap(int row, int column) {
        this.row = row;
        this.column = column;
        this.mapSize = row * column;
        this.entities = new HashMap<>();
    }

    public List<Coordinates> getCoordinatesByEntityType(Class<? extends Entity> searchingType) {
        List<Coordinates> resultsEntities = new ArrayList<>();

        for (java.util.Map.Entry<Coordinates, Entity> e : entities.entrySet()) {
            if (searchingType.isInstance(e.getValue())) {
                resultsEntities.add(e.getKey());
            }
        }
        return resultsEntities;
    }

    public void set(Coordinates coordinates, Entity entity) {
        if (entity == null || !isCoordinatesValid(coordinates)) {
            throw new NullPointerException();
        }
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {

        isCoordinatesValid(coordinates);
        entities.remove(coordinates);
    }

    public boolean coordinatesIsEmpty(Coordinates key) {
        isCoordinatesValid(key);
        return !entities.containsKey(key);
    }

    public Entity getEntity(Coordinates coordinates) {
        isCoordinatesValid(coordinates);
        Entity entity = entities.get(coordinates);
        if (entity == null) {
            throw new NullPointerException();
        }
        return entity;
    }

    private boolean isCoordinatesValid(Coordinates coordinates) {
        if (!isCoordinatesAvailable(coordinates)&& getEntity(coordinates)!=null ) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public boolean isCoordinatesAvailable(Coordinates current) {
        return current.row <= row && current.row >= MIN_COORDINATES_OF_THE_WORLD &&
                current.column <= column && current.column >= MIN_COORDINATES_OF_THE_WORLD;
    }

}
