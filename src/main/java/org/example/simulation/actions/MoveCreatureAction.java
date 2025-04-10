package org.example.simulation.actions;

import org.example.simulation.entities.creatures.Creature;
import org.example.simulation.map.Coordinates;
import org.example.simulation.map.WorldMap;
import org.example.simulation.actions.utils.Days;

import java.util.List;

public class MoveCreatureAction implements Action {

    private final Days daysCounter;

    public MoveCreatureAction(Days daysCounter) {
        this.daysCounter = daysCounter;
    }

    @Override
    public void execute(WorldMap worldMap) {
        moveCreatures(worldMap);
    }

    private void moveCreatures(WorldMap worldMap) {
        daysCounter.setNextDay();

        List<Coordinates> coordinates = worldMap.getCoordinatesByEntityType(Creature.class);
        for (Coordinates coordinate : coordinates) {
            if (!worldMap.coordinatesIsEmpty(coordinate)) {
                Creature creature = (Creature) worldMap.getEntity(coordinate);
                if (isTimeToWalk(creature)) {
                    creature.makeMove(worldMap, coordinate);
                }
            }
        }
    }


    private boolean isTimeToWalk(Creature creature) {
        return daysCounter.getDaysCounter() % creature.getSpeed() == 0;
    }


}