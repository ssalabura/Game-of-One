package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import org.bitbucket.GameofOneTeam.gameofone.Controller.GameController;
import org.bitbucket.GameofOneTeam.gameofone.Model.ClassicGameModel;

public class VictoryScreen extends Scene {
    private static Font btnStyle = Font.font("Ubuntu Mono",50);
    private static StackPane victoryRoot = new StackPane();
    private static VBox vb = new VBox(50);
    private static VBox button = new VBox(20);
    private static Button backToMenuButton = new Button();
    private static Text title = new Text("Game Finished!");
    private static Text winner = new Text();
    private static int winnerNum = 0;
    public VictoryScreen(int w, int h) {
        super(victoryRoot, w, h);
    }
    public void setWinner(int i){
        title.setFont(Font.font("Open Sans",150));
        title.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 5;");
        backToMenuButton.setFont(btnStyle);
        backToMenuButton.setText("Return to Main Menu");
        backToMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                View.stage.setScene(View.mainMenu);
            }
        });
        backToMenuButton.setFont(Font.font("Ubuntu Mono",20));
        backToMenuButton.setMaxSize(250,30);
        button.getChildren().add(backToMenuButton);
        button.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(title,winner,button);
        vb.setAlignment(Pos.CENTER);
        victoryRoot.getChildren().add(vb);
        victoryRoot.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        victoryRoot.getChildren().clear();
        winnerNum = i;
        if(winnerNum==0) winner = new Text("Congratulations, you win!");
        else winner = new Text("Player "+winnerNum+" wins! You lose!");

    }
}
