package org.example;


import org.example.simulation.Simulation;
import org.example.simulation.SimulationCreator;
import org.example.simulation.SimulationFactory;

public class Main {

    public static void main(String[] args) {
        SimulationCreator simulationCreator = new SimulationFactory();
        Simulation simulation = simulationCreator.createSimulation();
        simulation.startSimulation();
    }

}