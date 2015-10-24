package de.hsmannheim.gameoflife.controller;

import de.hsmannheim.gameoflife.controller.GameController;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* GameController Tester. 
* 
* @author <Authors Dennis, Alex>
* @since <pre>Okt 24, 2015</pre> 
* @version 1.0 
*/ 
public class GameControllerTest { 

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: generateGameField()
    *
    */
    @Test
    public void testGenerateDefaultGameField()  {
        GameController controller = new GameController();
        controller.generateGameField();

        Assert.assertNotNull(controller.getField());
    }


    @Test
    public void testGenerateSquareGameField()  {
        GameController controller = new GameController();
        controller.generateGameField(3);

        Assert.assertNotNull(controller.getField());
    }

    @Test
    public void testGenerateAnyGameField()  {
        GameController controller = new GameController();
        controller.generateGameField(3,7);

        Assert.assertNotNull(controller.getField());
    }

    @Test
    public void testGenerateRandomGameField() {
        GameController controller = new GameController();
        controller.generateRandomGameField();

        int checkedFields = countCheckedFields(controller);

        Assert.assertEquals(24, checkedFields);

    }

    @Test
    public void testGenerateRandomSquareGameField() {
        GameController controller = new GameController();
        controller.generateRandomGameField(5);

        int checkedFields = countCheckedFields(controller);

        Assert.assertEquals(7, checkedFields);
    }

    @Test
    public void testGenerateRandomAnyGameField() {
        GameController controller = new GameController();
        controller.generateRandomGameField(5, 8);

        int checkedFields = countCheckedFields(controller);

        Assert.assertEquals(12, checkedFields);
    }

    private int countCheckedFields(GameController controller) {
        int checkedFields = 0;

        for(int[] x : controller.getField().getFieldData()){
            for (int value : x){
                if (value == 1) {
                    checkedFields++;
                }
            }
        }
        return checkedFields;
    }


    @Test
    public void testChangeFieldCorrectChangingTo1() {
        GameController controller = new GameController();
        controller.generateGameField();
        controller.changeField(3,3,1);

        Assert.assertEquals(1, controller.getField().getFieldData()[3][3]);
    }

    @Test
    public void testChangeFieldWrongInput() {
        GameController controller = new GameController();
        controller.generateGameField();
        controller.changeField(3,3,5);

        Assert.assertEquals(0, controller.getField().getFieldData()[3][3]);
    }


    @Test
    public void testChangeFieldIndexOutOfBounds() {
        GameController controller = new GameController();
        controller.generateGameField();

        controller.changeField(10,3,1);

        controller.changeField(-1,3,1);

        controller.changeField(3,10,1);

        controller.changeField(3,-1,1);

    }

    @Test
    public void testStartAndStopTheGame() throws InterruptedException {
        GameController controller = new GameController();
        controller.generateRandomGameField();

        controller.startGame();

        Assert.assertTrue(controller.automatedGame.isAlive());

        controller.stopGame();

        Assert.assertNull(controller.automatedGame);

    }


} 
