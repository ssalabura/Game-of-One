package org.bitbucket.GameofOneTeam.gameofone.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VictoryScreen extends Scene {
    private static Font btnStyle = Font.font("Ubuntu Mono",50);
    private static StackPane root = new StackPane();
    private static VBox vb;
    private static VBox button;
    private static Button backToMenuButton = new Button();
    private static Text title = new Text("Game Finished!");
    private static Text winner = new Text();
    public VictoryScreen(int w, int h) {
        super(root, w, h);
    }
    public void show(int winnerNum) {
        button = new VBox(20);
        vb  = new VBox(50);
        root.getChildren().clear();
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
        if(winnerNum==0) winner = new Text("Congratulations, you win!");
        else winner = new Text("Player "+winnerNum+" wins! You lose!");
        winner.setFont(Font.font("Open Sans",80));
        winner.setStyle("-fx-fill: white;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2;");
        vb.getChildren().addAll(title,winner,button);
        vb.setAlignment(Pos.CENTER);
        root.getChildren().add(vb);
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        View.stage.setScene(this);
    }

    static void reloadTextures() {
        root.setBackground(new Background(new BackgroundImage(View.background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
