package org.example.simulation.actions;

import org.example.simulation.map.WorldMap;
import org.example.simulation.actions.utils.Days;
import org.example.simulation.consoleRenderers.ConsoleMessagesRenderer;
import org.example.simulation.consoleRenderers.WorldConsoleRenderer;

public class SimulationRenderAction implements Action{
    private final Days daysCounter;

    public SimulationRenderAction(Days daysCounter) {
        this.daysCounter = daysCounter;
    }

    @Override
    public void execute(WorldMap worldMap) {
        WorldConsoleRenderer.render(worldMap);
        ConsoleMessagesRenderer.output(daysCounter.toString());
    }
}
