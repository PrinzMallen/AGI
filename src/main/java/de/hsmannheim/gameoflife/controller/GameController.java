package de.hsmannheim.gameoflife.controller;

import de.hsmannheim.gameoflife.model.GridField;

import java.util.Random;

/**
 * Created by Dennis, Alex on 24.10.2015.
 */
public class GameController {

    protected GridField field;

    public void startGame() {
        if (field != null) {
            int numberOfFields = field.getFieldData().length * field.getFieldData()[0].length;
            int numberOfGeneratedData = (int) (numberOfFields * 0.1);
            Random random =new Random();
            while(numberOfGeneratedData>0){
                int x=random.nextInt(field.getFieldData().length-1);
                int y=random.nextInt(field.getFieldData()[0].length-1);
                if(field.getFieldData()[x][y]!=1){
                    field.getFieldData()[x][y]=1;
                    numberOfGeneratedData--;
                }
            }

        }

    }

    public void stopGame() {

    }


    public GridField generateGameField() {
        field = new GridField();
        return field;
    }

    public GridField generateGameField(int size) {
        field = new GridField(size);
        return field;
    }

    public GridField generateGameField(int xSize, int ySize) {
        field = new GridField(xSize, ySize);
        return field;
    }

    public GridField updateField() {
        if (field != null) {
            // TODO: field = generateNextStep();
            return field;
        }

        return null;
    }

    public GridField changeField(int x, int y, int value) {
        if (field != null && (value == 0 || value == 1)) {

            if (checkIfFieldDataExists(x, y)) {
                field.getFieldData()[x][y] = value;
            }
        }

        return field;
    }

    private boolean checkIfFieldDataExists(int x, int y) {
        if (x >= 0 && y >= 0) {
            return x < field.getFieldData().length && y < field.getFieldData()[x].length;
        }

        return false;
    }


    public GridField getField() {
        return field;
    }

}
