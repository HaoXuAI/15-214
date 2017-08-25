package edu.cmu.cs.cs214.rec07.core;

/**
 * The TicTacToe interface is used by the GUI to report GUI related events to
 * the core implementation.
 */
public interface TicTacToe {
    /** Tic-Tac-Toe is played on a 3x3 grid */
    int GRID_SIZE = 3;

    /** The two players */
    enum Player { X, O }

    /**
     * Register a game change listener to be notified of game change events.
     *
     * @param listener The listener to be notified of game change events.
     */
    void addGameChangeListener(GameChangeListener listener);

    /**
     * Get the player that currently has the turn.
     * 
     * @return The player that currently has the turn.
     */
    Player getCurrentPlayer();

    /**
     * Get the player who placed a move in a given location.
     *
     * @param row The row associated with the desired location.
     *
     * @param col The column associated with the desired location.
     * @return The player who moved at this location or null if no move has been
     *         played at this location.
     */
    Player getSquare(int row, int col);

    /**
     * Attempts to place the current player's symbol on a square in the game
     * grid. If true is returned, then the move is valid and the change is made;
     * otherwise, the move is invaid and nothing is changed.
     *
     * @param row The row of the current player's move.
     *
     * @param col The column of the current player's move.
     * @throws IllegalStateException if the game has not yet been started.
     *
     * @return true if the move was valid and made, false otherwise false can
     *         mean that the coordinates were outside of the board or occupied
     *         already.
     */
    boolean playMove(int row, int col);

    /**
     * Starts (or restarts) the game.
     */
    void startNewGame();
}
