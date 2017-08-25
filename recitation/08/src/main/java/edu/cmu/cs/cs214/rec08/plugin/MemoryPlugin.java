package edu.cmu.cs.cs214.rec08.plugin;

import java.util.*;

import edu.cmu.cs.cs214.rec08.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec08.framework.core.GamePlugin;

/**
 * An example Memory game plug-in.
 */
public class MemoryPlugin implements GamePlugin {
    private static final String GAME_NAME = "Memory";

    // Invariant: GRID_WIDTH * GRID_HEIGHT % 2 == 0.
    private static final int GRID_WIDTH = 4;
    private static final int GRID_HEIGHT = 4;

    // Invariant: 2 * WORDS.length == GRID_HEIGHT * GRID_WIDTH.
    private static final String[] WORDS =
            { "Apple", "Boat", "Car", "Dog", "Eagle", "Fish", "Giraffe", "Helicopter" };

    private static final String SELECT_FIRST_CARD_MSG = "Select first card.";
    private static final String SELECT_SECOND_CARD_MSG = "Select second card.";
    private static final String MATCH_FOUND_MSG = "You found a match!";
    private static final String MATCH_NOT_FOUND_MSG = "Match not found.";
    private static final String PLAYER_WON_MSG = "All pairs found, %s won the game!";

    private GameFramework framework;
    private final List<String> cards = new ArrayList<>();
    private int firstFaceUpX, firstFaceUpY;
    private int secondFaceUpX, secondFaceUpY;
    private boolean lastMatch;
    private int clickCounter;
    private int numCardsFaceDown;

    // The cards at all locations. (Does not track the face-up / face down status.)
    private final String internalGameGrid[][] = new String[GRID_HEIGHT][GRID_WIDTH];

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return GRID_WIDTH;
    }

    @Override
    public int getGridHeight() {
        return GRID_HEIGHT;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
        for (String word : WORDS) {
            cards.add(word);
            cards.add(word);
        }
    }

    @Override
    public void onNewGame() {
        Collections.shuffle(cards);
        Iterator<String> it = cards.iterator();
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x =0; x < GRID_WIDTH; x++) {
                internalGameGrid[y][x] = it.next();
            }
        }
        numCardsFaceDown = GRID_HEIGHT * GRID_WIDTH;
    }

    @Override
    public void onNewMove() {
        clickCounter = 0;
        framework.setFooterText(SELECT_FIRST_CARD_MSG);
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return framework.getSquare(x, y) == null;
    }

    @Override
    public boolean isMoveOver() {
        return clickCounter > 1;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        switch (clickCounter) {
            case 0:
                if (!lastMatch) {
                    framework.setSquare(firstFaceUpX,  firstFaceUpY,  null);
                    framework.setSquare(secondFaceUpX, secondFaceUpY, null);
                }
                framework.setSquare(x, y, internalGameGrid[y][x]);
                firstFaceUpX = x;
                firstFaceUpY = y;
                framework.setFooterText(SELECT_SECOND_CARD_MSG);
                break;
            case 1:
                framework.setSquare(x, y, internalGameGrid[y][x]);
                secondFaceUpX = x;
                secondFaceUpY = y;
                framework.setFooterText(isMatch() ? MATCH_FOUND_MSG : MATCH_NOT_FOUND_MSG);
                break;
            default:
                throw new AssertionError("Bad click counter value: " + clickCounter);
        }
        clickCounter++;
    }

    /** Side effects: sets lastMatch, updates numCovered */
    private boolean isMatch() {
        String card1 = internalGameGrid[firstFaceUpY][firstFaceUpX];
        String card2 = internalGameGrid[secondFaceUpY][secondFaceUpX];
        if (lastMatch = card1.equals(card2)) {
            numCardsFaceDown -= 2;
        }
        return lastMatch;
    }

    @Override
    public boolean isGameOver() {
        return numCardsFaceDown == 0;
    }

    @Override
    public String getGameOverMessage() {
        return String.format(PLAYER_WON_MSG, framework.getCurrentPlayer().getName());
    }

	@Override
	public void onGameClosed() {
	}
}
