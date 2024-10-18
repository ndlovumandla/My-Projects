package ui;

import game.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameUI extends Application {
    private Game game;
    private Label playerInfoLabel;
    private TextArea gameLog;

    @Override
    public void start(Stage primaryStage) {
        game = new Game("Hero");

        // Layout and UI elements
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setVgap(10);
        root.setHgap(10);
        
        playerInfoLabel = new Label("Player: " + game.getPlayer().getName());
        playerInfoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        gameLog = new TextArea("Welcome to the game!\n");
        gameLog.setEditable(false);
        gameLog.setWrapText(true);

        Button interactButton = new Button("Interact with NPC");
        interactButton.setOnAction(e -> interactWithNPC());

        Button attackButton = new Button("Attack NPC");
        attackButton.setOnAction(e -> attackNPC());

        Button saveButton = new Button("Save Game");
        saveButton.setOnAction(e -> saveGame());

        Button loadButton = new Button("Load Game");
        loadButton.setOnAction(e -> loadGame());

        root.add(playerInfoLabel, 0, 0);
        root.add(gameLog, 0, 1, 2, 1);
        root.add(interactButton, 0, 2);
        root.add(attackButton, 1, 2);
        root.add(saveButton, 0, 3);
        root.add(loadButton, 1, 3);

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("Text-Based Adventure Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void interactWithNPC() {
        game.getNPC().interact(game.getPlayer());
        gameLog.appendText("Interacted with NPC: " + game.getNPC().getName() + ".\n");
        updatePlayerInfo();
    }

    private void attackNPC() {
        game.getPlayer().attack(game.getNPC());

        // Check if the NPC is alive
        if (!game.getNPC().isAlive()) {
            gameLog.appendText(game.getNPC().getName() + " has been defeated!\n");
            game.createRandomNPC(); // Create a new NPC after defeating the current one
            gameLog.appendText("A new NPC has appeared: " + game.getNPC().getName() + "!\n");
        } else {
            gameLog.appendText(game.getNPC().getName() + " has " + game.getNPC().getHealth() + " health remaining.\n");
        }

        // NPC attacks player
        if (game.getNPC().isAlive()) {
            game.getNPC().attack(game.getPlayer());
            if (!game.getPlayer().isAlive()) {
                gameLog.appendText("Game Over! " + game.getPlayer().getName() + " has been defeated!\n");
            } else {
                updatePlayerInfo();
            }
        }

        updatePlayerInfo();
    }

    private void saveGame() {
        game.saveGameState();
        gameLog.appendText("Game state saved.\n");
    }

    private void loadGame() {
        game.loadGameState();
        gameLog.appendText("Game state loaded.\n");
        updatePlayerInfo();
    }

    private void updatePlayerInfo() {
        playerInfoLabel.setText("Player: " + game.getPlayer().getName() +
                " | Health: " + game.getPlayer().getHealth() +
                " | Strength: " + game.getPlayer().getStrength() +
                " | Defense: " + game.getPlayer().getDefense() +
                " | Experience: " + game.getPlayer().getExperience());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
