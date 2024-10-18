package models;

public abstract class Character {
    protected String name;
    protected int health;
    protected int strength;
    protected int defense;

    public Character(String name, int health, int strength, int defense) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
    }

    public void attack(Character opponent) {
        int damage = this.strength - opponent.defense;
        if (damage > 0) {
            opponent.health -= damage;
            System.out.println(this.name + " attacks " + opponent.name + " for " + damage + " damage!");
        } else {
            System.out.println(this.name + " attacks " + opponent.name + " but it's not effective.");
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public abstract void useSpecialAbility(Character opponent);

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void showStats() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Strength: " + strength);
        System.out.println("Defense: " + defense);
    }
}
