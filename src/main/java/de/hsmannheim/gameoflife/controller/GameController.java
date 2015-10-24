package de.hsmannheim.gameoflife.controller;

import de.hsmannheim.gameoflife.model.GridField;

/**
 * Created by Dennis, Alex on 24.10.2015.
 */
public class GameController {

    protected GridField field;

    public void startGame() {
        generateGameField();
    }

    public GridField generateGameField() {
        field = new GridField();
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
