package org.example.simulation.map;

import org.example.simulation.entities.Entity;

import java.util.*;

public class Coordinates {

    public final int row;
    public final int column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinates getRandomCoordinates(WorldMap worldMap){
        int row = getRandomValue(worldMap.row);
        int column = getRandomValue(worldMap.column);
        return new Coordinates(row,column);
    }

    public static Coordinates getRandomAvailableCoordinate(WorldMap worldMap) {
        Coordinates randomCoordinate = getRandomCoordinates(worldMap);
        List<Coordinates> mapCoordinates = worldMap.getCoordinatesByEntityType(Entity.class);

        while (mapCoordinates.contains(randomCoordinate)) {
            randomCoordinate = getRandomCoordinates(worldMap);
        }
        return randomCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    private static int getRandomValue(int boundsValue){
        return WorldMap.MIN_COORDINATES_OF_THE_WORLD + (int) (Math.random() *
                (boundsValue - WorldMap.MIN_COORDINATES_OF_THE_WORLD + 1));
    }
}
