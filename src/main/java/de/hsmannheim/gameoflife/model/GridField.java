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
        this(size, size);
    }

    public GridField(int xSize, int ySize) {
        fieldData = new int[xSize][ySize];
    }

    public void setFieldData(int[][] fieldData) {
        this.fieldData = fieldData;
    }

    public int[][] getFieldData() {
        return fieldData;
    }
}
