package de.hsmannheim.gameoflife.model;

import de.hsmannheim.gameoflife.model.GridField;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* GridField Tester. 
* 
* @author <Authors Dennis, Alex>
* @since <pre>Okt 24, 2015</pre> 
* @version 1.0 
*/ 
public class GridFieldTest { 

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testDefaultCunstructor() {
        GridField defaultField = new GridField();

        Assert.assertEquals(9, defaultField.getFieldData().length);
        Assert.assertEquals(9, defaultField.getFieldData()[0].length);
    }


    @Test
    public void testOtherCunstructorSquareGrid() {
        GridField defaultField = new GridField(3);

        Assert.assertEquals(3, defaultField.getFieldData().length);
        Assert.assertEquals(3, defaultField.getFieldData()[0].length);
    }

    @Test
    public void testOtherCunstructorXAndYDiffrent() {
        GridField defaultField = new GridField(3, 5);

        Assert.assertEquals(3, defaultField.getFieldData().length);
        Assert.assertEquals(5, defaultField.getFieldData()[0].length);
    }


} 
