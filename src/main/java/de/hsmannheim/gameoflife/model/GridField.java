package de.hsmannheim.gameoflife.model;

import java.io.*;

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

    public void save(String path){
        try {
            FileOutputStream fOut=new FileOutputStream(path);
            ObjectOutputStream oOut=new ObjectOutputStream(fOut);
            oOut.writeObject(fieldData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String path){
        try {
            FileInputStream fIn=new FileInputStream(path);
            ObjectInputStream oIn=new ObjectInputStream(fIn);
            Object obj =oIn.readObject();
            if(obj instanceof int[][]){
                fieldData= (int[][]) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
