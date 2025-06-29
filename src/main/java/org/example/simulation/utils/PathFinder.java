package org.example.simulation.utils;

import org.example.simulation.astur_algorithm.AStar;
import org.example.simulation.astur_algorithm.GoalHasBeenNotFoundedException;
import org.example.simulation.entities.creatures.Creature;
import org.example.simulation.map.Coordinates;
import org.example.simulation.map.WorldMap;

import java.util.Deque;

public class PathFinder {
    private final WorldMap worldMap;

    public PathFinder(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public Coordinates getNextStep(Coordinates currentCoordinates ){
        AStar searcher = new AStar(worldMap);
        try {
            Creature currentCreature = (Creature) worldMap.getEntity(currentCoordinates);
            Deque<Coordinates> path = searcher.getPath(currentCoordinates, currentCreature.getTarget());
            path.removeFirst();
            return  path.getFirst();
        } catch (GoalHasBeenNotFoundedException e) {
            return new Coordinates(currentCoordinates.row, currentCoordinates.column);
        }

    }

}
