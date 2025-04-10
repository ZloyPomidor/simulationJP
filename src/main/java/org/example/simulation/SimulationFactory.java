package org.example.simulation;

import org.example.simulation.actions.*;
import org.example.simulation.actions.utils.Days;
import org.example.simulation.map.WorldMap;
import org.example.simulation.entities.Utils.EntitiesFactory;

import java.util.List;

public final class SimulationFactory implements SimulationCreator {
    private final static int DEFAULT_WORLD_MAP_ROW = 60;
    private final static int DEFAULT_WORLD_MAP_COLUMN = 40;
    private final WorldMap world;
    private final EntitiesFactory spawnData;
    private final Days days = new Days();
    private final List<Action> initActions;
    private final List<Action> turnActions;

    public SimulationFactory() {
        this.world = new WorldMap(DEFAULT_WORLD_MAP_ROW, DEFAULT_WORLD_MAP_COLUMN);
        this.spawnData = new EntitiesFactory();
        this.initActions = List.of(new SpawnEntityAction(spawnData), new SimulationRenderAction(days));
        this.turnActions= List.of(new AddEntityAction(spawnData), new MoveCreatureAction(days), new SimulationRenderAction(days));
    }

    public SimulationFactory(int row, int column) {
        this.world = new WorldMap(row, column);
        this.spawnData = new EntitiesFactory();
        this.initActions = List.of(new SpawnEntityAction(spawnData), new SimulationRenderAction(days));
        this.turnActions= List.of(new AddEntityAction(spawnData), new MoveCreatureAction(days), new SimulationRenderAction(days));
    }

    public SimulationFactory(int row, int column, double predator, double herbivore, double grass, double tree, double rock) {
        this.world = new WorldMap(row, column);
        this.spawnData = new EntitiesFactory(predator, herbivore, grass, tree, rock);
        this.initActions = List.of(new SpawnEntityAction(spawnData), new SimulationRenderAction(days));
        this.turnActions= List.of(new AddEntityAction(spawnData), new MoveCreatureAction(days), new SimulationRenderAction(days));
    }

    @Override
    public Simulation createSimulation() {
        return new Simulation(world,initActions,turnActions);
    }
}
