package org.example.simulation.pathFinder.asturAlgorithm;

import org.example.simulation.map.Coordinates;

public class Node extends Coordinates {
    private int g;
    private int f;
    private int h;
    private Node parent;

    public Node(Coordinates coordinates) {
        super(coordinates.row, coordinates.column);
    }


    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public Node getParent() {
        return parent;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

}
