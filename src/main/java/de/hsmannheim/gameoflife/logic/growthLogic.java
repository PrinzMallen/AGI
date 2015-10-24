package de.hsmannheim.gameoflife.logic;

/**
 *
 * @author Pat
 */
public class GrowthLogic {

    public static int countNeighbours(int[][] array, int x, int y) {

        int neighboursTotal = 0;

        //Eckpunkte --------------------------------------------------------------------
        //links oben
        if (y == 0 & x == 0) {

            neighboursTotal = array[array.length - 1][array[0].length - 1]
                    + array[0][array[0].length - 1]
                    + array[x + 1][array[0].length - 1]
                    + array[array.length - 1][0]
                    + array[x + 1][0]
                    + array[array.length - 1][y + 1]
                    + array[x][y + 1]
                    + array[x + 1][y + 1];
        } //links unten
        else if (x == 0 & y == array[0].length - 1) {

            neighboursTotal = array[array.length - 1][array[0].length - 2]
                    + array[x][array[0].length - 2]
                    + array[x + 1][array[0].length - 2]
                    + array[array.length - 1][array[0].length - 1]
                    + array[array.length - 1][y]
                    + array[array.length - 1][0]
                    + array[0][0]
                    + array[1][0];
        } //rechts unten
        else if (x == array.length - 1 & y == array[0].length - 1) {

            neighboursTotal = array[array.length - 2][array[0].length - 2]
                    + array[array.length - 1][array[0].length - 2]
                    + array[0][array[0].length - 2]
                    + array[array.length - 2][array[0].length - 1]
                    + array[0][array[0].length - 1]
                    + array[array.length - 2][0]
                    + array[array.length - 1][0]
                    + array[0][0];
        } //rechts oben
        else if (x == array.length - 1 & y == 0) {

            neighboursTotal = array[array.length - 2][array[0].length - 1]
                    + array[array.length - 1][array[0].length - 1]
                    + array[0][array[0].length - 1]
                    + array[array.length - 2][0]
                    + array[0][0]
                    + array[array.length - 2][1]
                    + array[array.length - 1][1]
                    + array[0][1];
        } //Ränder ---------------------------------------------------------------
        //linker Rand
        else if (x == 0) {

            neighboursTotal = array[array.length - 1][y - 1]
                    + array[x][y - 1]
                    + array[x + 1][y - 1]
                    + array[array.length - 1][y]
                    + array[x + 1][y]
                    + array[array.length - 1][y + 1]
                    + array[x][y + 1]
                    + array[x + 1][y + 1];

        } //unterer Rand
        else if (y == array[0].length - 1) {
            neighboursTotal = array[x - 1][y - 1]
                    + array[x][y - 1]
                    + array[x + 1][y - 1]
                    + array[x - 1][y]
                    + array[x + 1][y]
                    + array[x - 1][0]
                    + array[x][0]
                    + array[x + 1][0];
        } //rechte Rand
        else if (x == array.length - 1) {
            neighboursTotal = array[x - 1][y - 1]
                    + array[x][y - 1]
                    + array[0][y - 1]
                    + array[x - 1][y]
                    + array[0][y]
                    + array[x - 1][y + 1]
                    + array[x][y + 1]
                    + array[0][y + 1];
        } //oberer Rand
        else if (y == 0) {
            neighboursTotal = array[x - 1][array[0].length - 1]
                    + array[x][array[0].length - 1]
                    + array[x + 1][array[0].length - 1]
                    + array[x - 1][y]
                    + array[x + 1][y]
                    + array[x - 1][y + 1]
                    + array[x][y + 1]
                    + array[x + 1][y + 1];
        } //----------------------------------------------------------------------
        //allgemeiner Fall (keine Rand, keine Ecke)
        else {
            neighboursTotal = array[x - 1][y - 1]
                    + array[x][y - 1]
                    + array[x + 1][y - 1]
                    + array[x - 1][y]
                    + array[x + 1][y]
                    + array[x - 1][y + 1]
                    + array[x][y + 1]
                    + array[x + 1][y + 1];
        }
        return neighboursTotal;
    }

    public static int[][] createNextGeneration(int[][] oldGrid) {
        int[][] newGrid = new int[oldGrid.length][oldGrid[0].length];
        
            for(int i = 0; i < oldGrid.length; i++){
                for(int j = 0; j < oldGrid[0].length;j++){
                    
                    int neighbours = countNeighbours(oldGrid,i,j);
                    
                    
                    
                    if(oldGrid[i][j]==1){
                        //Rule 3: Overcrowding
                        if(neighbours > 3){
                            newGrid[i][j]=0;
                        }
                        //Rule 2: Survival
                        if(neighbours == 2 || neighbours == 3){
                            newGrid[i][j]=1;
                        }
                        //Rule 1 : Underpopulation
                        if(neighbours < 2){
                            newGrid[i][j]=0;
                        }
                    }
                    
                    //Rule 4: Reproduction
                    else {
                        if(neighbours==3){
                            newGrid[i][j]=1;
                        }
                    }
                    
                    
            }
            }
        
        return newGrid;
    }

}
