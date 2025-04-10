package org.example.simulation;

import org.example.simulation.map.WorldMap;
import org.example.simulation.consoleRenderers.ConsoleMessagesRenderer;
import org.example.simulation.actions.Action;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;



public class Simulation {

    private static final String STRING_MESSAGE = "Please select an action:  - %s (START) or %s - (EXIT)";
    public static final String ENDING_MESSAGE = "The simulation is complete.";
    private static final String EXIT = "E";
    private static final String NEXT = "N";
    private static final String START_STOP = "S";
    private static final int VALID_STRING_LENGTH = 1;
    private static final int FIRST_CHARACTER_POSITION = 0;
    private static final int ITERATION_TIME = 1500;
    private final WorldMap worldMap;
    private final Scanner scanner = new Scanner(System.in);
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private String userPressed;
    private boolean running= false;
    private boolean finished = false;
    private boolean initCompleted = false;

    public Simulation(WorldMap worldMap, List<Action> initActions, List<Action> turnActions) {
        this.worldMap = worldMap;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation() {
        Thread controlThread = new Thread(() -> {
            startingInteractionWithUser();
            defaultInteractionWithUser();
        });
        controlThread.start();
        endlessSimulation();

    }

    public synchronized void start(){
        this.notifyAll();
    }

    public synchronized void stop(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeActions(List<Action> actions){
        for(Action c:actions){
            c.execute(worldMap);
        }
    }

    private void endlessSimulation() {
        while (!finished) {
            waitingOfIterationTime();

            if (running) {
                performInit();
                executeActions(turnActions);
            }else
                stop();
        }
        ConsoleMessagesRenderer.output(ENDING_MESSAGE);
    }

    private void waitingOfIterationTime(){
        try {
            Thread.sleep(ITERATION_TIME);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    private void performInit() {
        if (!initCompleted){
            executeActions(initActions);
            initCompleted = true;
            waitingOfIterationTime();
        }
    }

    private boolean inputIsValid(String userPressed) {
        if (userPressed.length()==VALID_STRING_LENGTH){
            char input = userPressed.charAt(FIRST_CHARACTER_POSITION);
            if(Character.isLetter(input)){
                return isInputAvailable(userPressed);
            }
        }
        return false;
    }

    private boolean isInputAvailable(String userPressed) {
        List<String> actions = List.of(START_STOP,NEXT,EXIT);
        userPressed = userPressed.toUpperCase();
        return actions.contains(userPressed);
    }

    private void exit() {
        running=false;
        finished=true;
        start();
        Thread.currentThread().interrupt();
    }

    private void defaultInteractionWithUser(){
        while (!Thread.currentThread().isInterrupted()) {
            userPressed = scanner.nextLine();
            if (userPressed.equalsIgnoreCase(START_STOP)) {
                running = false;
                while (!running&&!Thread.currentThread().isInterrupted()){
                    chooseAnAction();
                }
            }else
                ConsoleMessagesRenderer.output(ConsoleMessagesRenderer.INCORRECT_INPUT_MESSAGE);
        }
    }

    private void chooseAnAction() {
        userPressed = scanner.nextLine();
        if(inputIsValid(userPressed)){
            userPressed = userPressed.toUpperCase(Locale.ROOT);
            switch (userPressed){
                case START_STOP ->running();
                case NEXT -> executeActions(turnActions);
                case EXIT -> exit();
            }
        }else
            ConsoleMessagesRenderer.output(ConsoleMessagesRenderer.INCORRECT_INPUT_MESSAGE);
    }

    private void startingInteractionWithUser(){
        while (!finished&&!running){
            ConsoleMessagesRenderer.output(String.format(STRING_MESSAGE, START_STOP, EXIT));
            userPressed = scanner.nextLine().toUpperCase();

            switch (userPressed){
                case START_STOP ->running();
                case EXIT -> exit();
                default -> ConsoleMessagesRenderer.output(ConsoleMessagesRenderer.INCORRECT_INPUT_MESSAGE);
            }
        }
    }

    private void running() {
        running = true;
        start();
    }
}
