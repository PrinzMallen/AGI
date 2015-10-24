/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsmannheim.gameoflife;

import de.hsmannheim.gameoflife.controller.GameController;
import de.hsmannheim.gameoflife.view.MainView;

/**
 *
 * @author Alex
 */
public class Main {
    public static void main(String[] argu){
    	GameController gameController = new GameController();
        MainView mainView = new MainView(gameController);
        
    }
}
