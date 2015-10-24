package de.hsmannheim.gameoflife.model;

/**
 * Created by Dennis & Alex on 24.10.2015.
 */
public class GridField {

    private int[][] field;

    private static final int DEFAULT_SIZE = 9;

    public GridField () {
        field = new int[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public int[][] getField() {
        return field;
    }
}
