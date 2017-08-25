package edu.cmu.cs.cs214.rec07.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The implementation of the Tic-Tac-Toe game. You do not need to look at this
 * file in order to develop a UI for the game. The Tic-Tac-Toe interface should
 * have provided sufficient information.
 */
public class TicTacToeImpl implements TicTacToe {
    /** The players in this game */
    private final List<Player> players = Arrays.asList(Player.values());

    /** The listeners who will be notified of changes in the game state */
    private final List<GameChangeListener> gameChangeListeners  = new ArrayList<>();

    /** The game board. First index is row, second index is column. */
    private final Player gameGrid[][] = new Player[GRID_SIZE][GRID_SIZE];

    /** The index of the current player in players (0 is X, 1 is O) */
    private int currentPlayerIndex;

    /** Create a game of Tic-Tac-Toe. */
    public TicTacToeImpl() {
    }

    @Override
    public void addGameChangeListener(GameChangeListener listener) {
        gameChangeListeners.add(listener);
    }

    @Override
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    @Override
    public Player getSquare(int row, int col) {
        return gameGrid[row][col];
    }

    @Override
    public boolean playMove(int row, int col) {
        if (row < 0 || row > GRID_SIZE || col < 0 || col > GRID_SIZE) {
            throw new IllegalArgumentException("Illegal move: " + col + ", " + row);
        }
        if (gameGrid[row][col] != null) {
            return false;
        }

        gameGrid[row][col] = getCurrentPlayer();
        notifyMoveMade(col, row);
        checkGameEnd();
        switchPlayers();

        return true;
    }

    @Override
    public void startNewGame() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                gameGrid[row][col] = null;
                notifyMoveMade(col, row);
            }
        }
        currentPlayerIndex = 0;
        notifyPlayerChanged();
    }

    private void switchPlayers() {
        currentPlayerIndex = 1 - currentPlayerIndex; // Oscillates between 0 and 1
        notifyPlayerChanged();
    }

    private void notifyMoveMade(int col, int row) {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.squareChanged(row, col);
        }
    }

    private void notifyPlayerChanged() {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.currentPlayerChanged(getCurrentPlayer());
        }
    }

    private void notifyGameEnd(Player winner) {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.gameEnded(winner);
        }
    }

    private void checkGameEnd() {
        if (hasWon()) {
            notifyGameEnd(getCurrentPlayer());
        } else if (isFull()) {
            notifyGameEnd(null);
        }
    }

    /**
     * Checks if the current player has won the game.
     */
    private boolean hasWon() {
        // Check for a horizontal win.
        for (int row = 0; row < GRID_SIZE; row++) {
            if (isWin(row, 0, 0, 1))
                return true;
        }

        // Check for a vertical win.
        for (int col = 0; col < GRID_SIZE; col++) {
            if (isWin(0, col, 1, 0))
                return true;
        }

        // Check for a diagonal win.
        return isWin(0, 0, 1, 1) || isWin(0, GRID_SIZE - 1, 1, -1);
    }

    /**
     * Checks for a win on the line consisting  of (startRow, startCol),
     * (startRow + rowInc, startCol + colInc), and
     * (startRow + 2 * rowInc, startCol + 2 * colInc)
     */
    private boolean isWin(int startRow, int startCol, int rowInc, int colInc) {
        Player currentPlayer = getCurrentPlayer();
        for(int i = 0; i < GRID_SIZE; i++) {
            if (gameGrid[startRow][startCol] != currentPlayer)
                return false;
            startRow += rowInc;
            startCol += colInc;
        }
        return true;
    }

    /**
     * Checks if all of the squares in the game grid are occupied.
     */
    private boolean isFull() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (gameGrid[row][col] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
