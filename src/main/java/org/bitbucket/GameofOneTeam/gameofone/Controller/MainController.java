package org.bitbucket.GameofOneTeam.gameofone.Controller;

import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

public class MainController {
    public static void main(String... args){
        new GameController(new ClassicGameModel(true)).startGame();
    }
}
