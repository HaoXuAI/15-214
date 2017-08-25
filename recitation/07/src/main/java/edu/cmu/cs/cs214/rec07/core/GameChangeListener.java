package edu.cmu.cs.cs214.rec07.core;

import static edu.cmu.cs.cs214.rec07.core.TicTacToe.Player;

public interface GameChangeListener {
    /**
     * Called when any tile on the board changes. This
     * includes changes to initialize a fresh board.
     *
     * @param row The row of the updated cell on the board.
     * @param col The column of the updated cell on the board.
     */
    void squareChanged(int row, int col);

    /**
     * Called when the current player changed
     *
     * @param player The new current player.
     */
    void currentPlayerChanged(Player player);

    /**
     * Called when the game ends, announcing the winner (or null on a tie).
     *
     * @param winner The winner of the game, or null on a tie.
     */
    void gameEnded(Player winner);
}
