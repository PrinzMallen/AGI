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
        FileOutputStream fOut = null;
        ObjectOutputStream oOut = null;
        try {
            fOut=new FileOutputStream(path);
            oOut=new ObjectOutputStream(fOut);
            oOut.writeObject(fieldData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fOut != null) {
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oOut != null) {
                try {
                    oOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(String path){
        FileInputStream fIn = null;
        ObjectInputStream oIn =  null;
        try {
            fIn=new FileInputStream(path);
            oIn=new ObjectInputStream(fIn);
            Object obj =oIn.readObject();
            if(obj instanceof int[][]){
                fieldData= (int[][]) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fIn != null) {
                try {
                    fIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oIn != null) {
                try {
                    oIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
