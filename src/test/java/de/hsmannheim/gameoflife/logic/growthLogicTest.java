
package de.hsmannheim.gameoflife.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pat
 */
public class growthLogicTest {
    int[][] testgridCenter ;
    int[][] testgridCorners;
    int[][] testgridEdges ;
    
    public growthLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
 
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testgridCenter = new int[9][9];
        testgridCorners = new int[9][9];
        testgridEdges = new int[9][9];
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testField() {
        
        //int[][] testarray = new int[9][9];
        //testarray = grid1.getField();
        
        //assertEquals(testarray[4][4], 1);
    }
    
    @Test
    public void testCountNeighboursInCenter() {
        testgridCenter[4][4]=1;
      
        assertEquals(GrowthLogic.countNeighbours(testgridCenter, 4, 4),0);
        assertEquals(GrowthLogic.countNeighbours(testgridCenter, 5, 5),1);
    }
    
     @Test
    public void testCountNeighboursInCorner() {
        testgridCorners[0][0]=1;
        testgridCorners[0][1]=1;
        testgridCorners[1][0]=1;
        
        testgridCorners[testgridCorners.length-1][testgridCorners[0].length-1]=1;

        assertEquals(GrowthLogic.countNeighbours(testgridCorners, 0, 0),3);
        assertEquals(GrowthLogic.countNeighbours(testgridCorners, 0, 1),2);
        assertEquals(GrowthLogic.countNeighbours(testgridCorners, 1, 0),2);

    }
     @Test
    public void testCountNeighboursInEdges() {
        testgridEdges[4][4]=1;

    }
    
}
