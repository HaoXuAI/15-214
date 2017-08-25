package edu.cmu.cs.cs214.rec08.framework.core;

/**
 * Represents a player in the game. Each player has an associated name and
 * symbol (the first letter of the player's name).
 */
public class Player {
    private final String name;
    private final String symbol;

    /** Creates a player with the specified name. */
    public Player(String name) {
        this.name = name;
        symbol = name.substring(0, 1);
    }

    /** Returns this player's name */
    public String getName() {
        return name;
    }

    /** Returns this player's symbol. */
    public String getSymbol() {
        return symbol;
    }
}