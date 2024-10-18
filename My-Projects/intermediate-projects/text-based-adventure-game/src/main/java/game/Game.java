package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import models.HealerNPC;
import models.NPC;
import models.Player;
import models.RegularNPC;
import models.SpecialNPC;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization
    private Player player;
    private NPC npc;

    public Game(String playerName) {
        player = new Player(playerName);
        createRandomNPC();
    }

    public void createRandomNPC() {
        Random random = new Random();
        int npcType = random.nextInt(3); // 0: Regular, 1: Healer, 2: Special

        switch (npcType) {
            case 0:
                npc = new RegularNPC("Regular Joe");
                break;
            case 1:
                npc = new HealerNPC("Healing Hannah");
                break;
            case 2:
                npc = new SpecialNPC("Special Sam");
                break;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public NPC getNPC() {
        return npc;
    }

    public void saveGameState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_save.dat"))) {
            oos.writeObject(this); // Save the entire game object
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public void loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game_save.dat"))) {
            Game loadedGame = (Game) ois.readObject(); // Load the entire game object
            this.player = loadedGame.player; // Restore player
            this.npc = loadedGame.npc; // Restore NPC
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
}
