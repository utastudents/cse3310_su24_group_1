package uta.cse3310;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RoundTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RoundTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(RoundTest.class);
    }

    private Round round;
    private final String testFilePath = "src/test/resources/test_words.txt"; // Update the path to your test file

    public void setUp() {
        round = new Round(1);
        // Mocking loadWordsFromFile
        round.words = Arrays.asList("apple", "banana", "cherry");
    }

    public void testRoundInitialization() {
        assertEquals(1, round.getRoundNumber());
        assertNull(round.getStartTime());
        assertNull(round.getEndTime());
        assertEquals(0, round.getScore());
        assertEquals("not started", round.getStatus());
    }

    public void testStartRound() {
        round.startRound();
        assertNotNull(round.getStartTime());
        assertEquals("in progress", round.getStatus());
        assertNotNull(round.getCurrentWord());
        assertTrue(round.words.contains(round.getCurrentWord()));
    }

    public void testChooseRandomWord() {
        round.startRound();
        String firstWord = round.getCurrentWord();
        round.chooseRandomWord();
        String secondWord = round.getCurrentWord();
        // It's possible that the same word is chosen, so we can't assert that they're different
        assertNotNull(firstWord);
        assertNotNull(secondWord);
        assertTrue(round.words.contains(firstWord));
        assertTrue(round.words.contains(secondWord));
    }

    public void testCheckGuess() {
        round.startRound();
        String chosenWord = round.getCurrentWord();
        assertTrue(round.checkGuess(chosenWord));
        assertFalse(round.checkGuess(chosenWord + "wrong"));
    }

    public void testEndRound() {
        round.startRound();
        round.endRound();
        assertNotNull(round.getEndTime());
        assertEquals("completed", round.getStatus());
        assertTrue(round.getScore() >= 0);
    }

    public void testCalculateScore() {
        round.setStartTime(LocalDateTime.now().minusSeconds(5));
        round.setEndTime(LocalDateTime.now());
        assertEquals(5, round.calculateScore());
    }

    public void testRoundFlow() {
        round.startRound();
        assertEquals("in progress", round.getStatus());

        round.checkGuess(round.getCurrentWord());
        round.endRound();

        assertEquals("completed", round.getStatus());
        assertNotNull(round.getEndTime());
        assertTrue(round.getScore() > 0);
    }
}


