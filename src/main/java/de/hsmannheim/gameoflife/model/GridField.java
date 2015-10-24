package de.hsmannheim.gameoflife.model;

/**
 * Created by Dennis & Alex on 24.10.2015.
 */
public class GridField {

    private int[][] fieldData;

    private static final int DEFAULT_SIZE = 9;

    public GridField () {
        this(DEFAULT_SIZE);
    }

    public GridField (int size) {
        fieldData = new int[size][size];
    }

    public void setFieldData(int[][] fieldData) {
        this.fieldData = fieldData;
    }

    public int[][] getFieldData() {
        return fieldData;
    }
}
