package org.example.simulation.path_finder.asturAlgorithm;

public class GoalHasBeenNotFoundedException extends RuntimeException {
    private static final String GOAL_HAS_BEEN_NOT_FOUNDED_MESSAGE = "Goal has been not founded.";
    public GoalHasBeenNotFoundedException() {
        super(GOAL_HAS_BEEN_NOT_FOUNDED_MESSAGE);
    }
}
