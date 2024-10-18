package models;

import java.io.Serializable;

public class SpecialNPC extends NPC implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    // Special ability points
    private int abilityPoints;

    public SpecialNPC(String name) {
        super(name, 70, 15, 3);
        this.abilityPoints = 2; // Default special ability points
    }

    // Default constructor for serialization
    public SpecialNPC() {
        super("Special NPC", 70, 15, 3);
        this.abilityPoints = 2;
    }

    @Override
    public void interact(Character player) {
        // Implement special interaction logic here
        player.setHealth(player.getHealth() - 5); // Example: player loses health
        System.out.println(getName() + " used a special ability on " + player.getName());
    }

    @Override
    public void useSpecialAbility(Character opponent) {
        opponent.setHealth(opponent.getHealth() - 10); // Example of a special attack
        System.out.println(getName() + " used a special ability, dealing 10 damage!");
    }
}
