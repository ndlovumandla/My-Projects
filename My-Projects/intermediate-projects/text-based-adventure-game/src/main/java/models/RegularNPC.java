package models;

import java.io.Serializable;

public class RegularNPC extends NPC implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    public RegularNPC(String name) {
        super(name, 60, 10, 4);
    }

    // Default constructor for serialization
    public RegularNPC() {
        super("Regular NPC", 60, 10, 4);
    }

    @Override
    public void interact(Character player) {
        // Implement interaction logic here
        System.out.println(getName() + " has no special abilities.");
    }

    @Override
    public void useSpecialAbility(Character opponent) {
        // Not applicable for regular NPCs
        System.out.println(getName() + " cannot use special ability!");
    }
}
