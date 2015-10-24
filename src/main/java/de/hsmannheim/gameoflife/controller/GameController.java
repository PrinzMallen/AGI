package de.hsmannheim.gameoflife.controller;

import de.hsmannheim.gameoflife.logic.GrowthLogic;
import de.hsmannheim.gameoflife.model.GridField;

import java.util.*;

/**
 *
 * Created by Dennis, Alex on 24.10.2015.
 */
public class GameController extends Observable {

    protected GridField field;

    protected Thread automatedGame;

    private List<Observer> observers = new ArrayList<>();

    public void startGame() {
        automatedGame = new Thread() {
            boolean interrupted = false;

            @Override
            public void run() {
                while (!interrupted) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // Ignore
                    }
                    doNextIterativeStep();
                }
            }

            @Override
            public void interrupt() {
                interrupted = true;
            }
        };

        automatedGame.start();
    }

    public void stopGame() {
        if (automatedGame != null) {
            automatedGame.interrupt();
            automatedGame = null;
        }
    }

    public void saveGame(String path){
        field.save(path);
    }

    public void loadGame(String path){
        field.load(path);
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

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    public GridField generateRandomGameField() {
        generateGameField();

        randomizeFieldData();

        return field;
    }

    public GridField generateRandomGameField(int size) {
        generateGameField(size);

        randomizeFieldData();

        return field;
    }


    public GridField generateRandomGameField(int xSize, int ySize) {
        generateGameField(xSize, ySize);

        randomizeFieldData();

        return field;
    }

    private void notifyObserver() {
        for (Observer obs : observers) {
            obs.update(this, field);
        }
    }

    private void randomizeFieldData() {
        int numberOfFields = field.getFieldData().length * field.getFieldData()[0].length;
        int numberOfGeneratedData = (int) (numberOfFields * 0.3);
        Random random = new Random();
        while (numberOfGeneratedData > 0) {
            int x = random.nextInt(field.getFieldData().length - 1);
            int y = random.nextInt(field.getFieldData()[0].length - 1);
            if (field.getFieldData()[x][y] != 1) {
                field.getFieldData()[x][y] = 1;
                numberOfGeneratedData--;
            }
        }
        notifyObserver();
    }

    private void doNextIterativeStep() {
        if (field != null) {
            field.setFieldData(GrowthLogic.createNextGeneration(field.getFieldData()));
            notifyObserver();
        }
    }

    public GridField doNextSingleStep(){
        if (field != null) {
            field.setFieldData(GrowthLogic.createNextGeneration(field.getFieldData()));
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
        return x >= 0 && y >= 0 && x < field.getFieldData().length && y < field.getFieldData()[x].length;

    }


    public GridField getField() {
        return field;
    }

}
