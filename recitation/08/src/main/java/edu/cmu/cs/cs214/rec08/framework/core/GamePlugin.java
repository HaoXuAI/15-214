package edu.cmu.cs.cs214.rec08.framework.core;

/**
 * The game plug-in interface that plug-ins use to implement and register games
 * with the {@link GameFramework}.
 */
public interface GamePlugin {

    /**
     * Gets the name of the plug-in game.
     */
    String getGameName();

    /**
     * Gets the width (in squares) of the plug-in game's grid.
     */
    int getGridWidth();

    /**
     * Returns the width (in squares) of the plug-in game's grid.
     */
    int getGridHeight();

    /**
     * Called (only once) when the plug-in is first registered with the
     * framework, giving the plug-in a chance to perform any initial set-up
     * before the game has begun (if necessary).
     *
     * @param framework The {@link GameFramework} instance with which the plug-in
     *                  was registered.
     */
    void onRegister(GameFramework framework);

    /**
     * Called when a new game is about to begin.
     */
    void onNewGame();

    /**
     * Called when a new move is about to begin (i.e. immediately after
     * {@link #onNewGame()}, and before each subsequent move in the game). This
     * method will only be called if the last move was valid and did not end the
     * game.
     */
    void onNewMove();

    /**
     * Returns true if a move at location (x, y) is allowed (based on the game's
     * current state). Returns false otherwise.
     */
    boolean isMoveValid(int x, int y);

    /**
     * Returns true if the current move is over (based on the game's current
     * state). Returns false otherwise.
     */
    boolean isMoveOver();

    /**
     * Called when a move has been played.
     *
     * @param x The x coordinate of the grid square that has been played.
     * @param y The y coordinate of the grid square that has been played.
     */
    void onMovePlayed(int x, int y);

    /**
     * Returns true if the game is over (based on the game's current state).
     * Returns false otherwise.
     */
    boolean isGameOver();

    /**
     * Returns the message to display when this game is deemed to be over. This
     * method is called immediately after {@link #isGameOver()} returns true.
     */
    String getGameOverMessage();

    /**
     * Called when the plugin is closed to allow the plugin to do any final cleanup.
     */
    void onGameClosed();
}
