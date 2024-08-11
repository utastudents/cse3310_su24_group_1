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
    }

    public void testRoundInitialization() {
        assertEquals(1, round.getRoundNumber());
        //assertNull(round.getStartTime());
        //assertNull(round.getEndTime());
        assertEquals(0, round.getScore());
        assertEquals("not started", round.getStatus());
    }

    public void testStartRound() {
        round.startRound();
        //assertNotNull(round.getStartTime());
        assertEquals("in progress", round.getStatus());
        //assertNotNull(round.getCurrentWord());
        //assertTrue(round.words.contains(round.getCurrentWord()));
    }

    public void testChooseRandomWord() {
        round.startRound();
        // Choosing random words happens when the round starts

        System.out.println(round.currentWord1);
        System.out.println(round.currentWord2);
        System.out.println(round.currentWord3);
    }

    public void testEndRound() {
        round.startRound();
        round.endRound();
        //assertNotNull(round.getEndTime());
        assertEquals("completed", round.getStatus());
        assertTrue(round.getScore() >= 0);
    }

    public void testRoundFlow() {
        round.startRound();
        assertEquals("in progress", round.getStatus());

        //round.checkGuess(round.getCurrentWord());
        round.endRound();

        assertEquals("completed", round.getStatus());
        //assertNotNull(round.getEndTime());
        //assertTrue(round.getScore() > 0);
    }
}