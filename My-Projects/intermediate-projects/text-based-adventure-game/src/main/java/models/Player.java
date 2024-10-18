package models;

import java.io.Serializable;

public class Player extends Character implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization
    private int experience;

    public Player(String name) {
        super(name, 100, 10, 5);
        this.experience = 0;
    }

    // Default constructor for serialization
    public Player() {
        super("Player", 100, 10, 5);
        this.experience = 0;
    }

    @Override
    public void useSpecialAbility(Character opponent) {
        // Player's special ability can be defined here if needed
    }

    public int getExperience() {
        return experience;
    }

    public void gainExperience(int amount) {
        experience += amount;
    }

    public void levelUp() {
        // Logic for leveling up
        this.health += 10;
        this.strength += 2;
        this.defense += 1;
        System.out.println(name + " leveled up!");
    }
}
