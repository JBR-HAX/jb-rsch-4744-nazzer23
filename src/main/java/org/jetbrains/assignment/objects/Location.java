package org.jetbrains.assignment.objects;

public class Location {
    private String direction;
    private int steps;

    public Location(String direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String getDirection() {
        return direction.toUpperCase();
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}