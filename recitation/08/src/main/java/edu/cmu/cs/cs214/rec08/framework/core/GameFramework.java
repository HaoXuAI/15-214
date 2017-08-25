package edu.cmu.cs.cs214.rec08.framework.core;

import java.util.List;

/**
 * The interface by which {@link GamePlugin} instances can directly interact
 * with the game framework.
 */
public interface GameFramework {
    /**
     * Get the {@link Player} that currently has the move.
     */
    Player getCurrentPlayer();

    /**
     * Get the list of {@link Player}s that have been added to the framework.
     */
    List<Player> getPlayers();

    /**
     * Get the string associated with the grid square located at (x, y).
     *
     * @param x The x coordinate of the grid square.
     * @param y The y coordinate of the grid square.
     * @return The string associated with the grid square at location (x, y), or
     *         null if no string has been set at this location since the
     *         beginning of the game.
     */
    String getSquare(int x, int y);

    /**
     * Set the string associated with the grid square located at (x, y). The
     * framework will display the string, or the empty string if it is null.
     *
     * @param x The x coordinate of the grid square.
     * @param y The y coordinate of the grid square.
     * @param t The string to set at the grid square.
     */
    void setSquare(int x, int y, String t);

    /**
     * Sets the text to display at the bottom of the framework's display.
     *
     * @param text The text to display.
     */
    void setFooterText(String text);
}