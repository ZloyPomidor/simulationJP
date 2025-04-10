package org.example.simulation.actions.utils;

public class Days {
    private int daysCounter;

    public int getDaysCounter() {
        return daysCounter;
    }

    public void setNextDay() {
        daysCounter++;
    }

    @Override
    public String toString() {
        return "Day - " + daysCounter;
    }
}
