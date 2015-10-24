package de.hsmannheim.gameoflife;

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
    public void testGenerateGameField()  {
        GameController controller = new GameController();
        controller.generateGameField();

        Assert.assertNotNull(controller.getField());
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

    /**
    *
    * Method: updateField()
    *
    */
    @Test
    public void testUpdateField() throws Exception {
         //TODO: Test goes here...
    }


} 
