package models;

import java.io.Serializable;

public class HealerNPC extends NPC implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    public HealerNPC(String name) {
        super(name, 50, 5, 2);
    }

    // Default constructor for serialization
    public HealerNPC() {
        super("Healer NPC", 50, 5, 2);
    }

    @Override
    public void interact(Character player) {
        // Healer interaction logic
        player.setHealth(player.getHealth() + 10); // Heal player
        System.out.println(getName() + " heals " + player.getName() + " for 10 health!");
    }

    @Override
    public void useSpecialAbility(Character opponent) {
        // Not applicable for healer
        System.out.println(getName() + " cannot use special ability to attack!");
    }
}
