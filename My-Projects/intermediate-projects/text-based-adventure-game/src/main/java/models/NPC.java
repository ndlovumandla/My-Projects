package models;

import java.io.Serializable;

public abstract class NPC extends Character implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    public NPC(String name, int health, int strength, int defense) {
        super(name, health, strength, defense);
    }

    public abstract void interact(Character player);
}
