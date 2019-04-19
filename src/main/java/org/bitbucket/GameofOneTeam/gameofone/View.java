package org.bitbucket.GameofOneTeam.gameofone;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Application{

    @Override
    public void start(Stage primaryStage) {

        //Scene - Main Menu
        StackPane MainMenuRoot = new StackPane();
        final Scene MainMenu = new Scene(MainMenuRoot,800,600);
        {
            final VBox mainmenu = new VBox(50);
            final VBox vb = new VBox(20);
            final Button newgame = new Button();
            final Button settings = new Button();
            final Button exitgame = new Button();
            final Text title = new Text("Game of One");

            // System.out.println(Font.getFamilies()); -- all fonts

            title.setFont(Font.font("Open Sans",100));

            newgame.setText("New Game (WIP)");
            newgame.setMinSize(100,100);
            newgame.setFont(Font.font("Ubuntu Mono",50));
            newgame.setDisable(true);

            settings.setText("Settings (WIP)");
            settings.setMinSize(100,100);
            settings.setFont(Font.font("Ubuntu Mono",50));
            settings.setDisable(true);

            exitgame.setText("Exit Game");
            exitgame.setMinSize(100,100);
            exitgame.setFont(Font.font("Ubuntu Mono",50));
            exitgame.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    System.exit(0);
                }
            });

            vb.getChildren().addAll(newgame,settings,exitgame);
            vb.setAlignment(Pos.CENTER);
            mainmenu.getChildren().addAll(title,vb);
            mainmenu.setAlignment(Pos.CENTER);
            MainMenuRoot.getChildren().add(mainmenu);
        }
        //End of Scene - Main Menu

        primaryStage.setTitle("Game of One");
        primaryStage.setScene(MainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
