package uta.cse3310;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {

    private Round round;
    private final String testFilePath = "src/test/resources/test_words.txt"; // Update the path to your test file

    @BeforeEach
    public void setUp() {
        round = new Round(1);
        // Mocking loadWordsFromFile
        round.words = Arrays.asList("apple", "banana", "cherry");
    }

    @Test
    public void testRoundInitialization() {
        assertEquals(1, round.getRoundNumber());
        assertNull(round.getStartTime());
        assertNull(round.getEndTime());
        assertEquals(0, round.getScore());
        assertEquals("not started", round.getStatus());
    }

    @Test
    public void testStartRound() {
        round.startRound();
        assertNotNull(round.getStartTime());
        assertEquals("in progress", round.getStatus());
        assertNotNull(round.getCurrentWord());
        assertTrue(round.words.contains(round.getCurrentWord()));
    }

    @Test
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

    @Test
    public void testCheckGuess() {
        round.startRound();
        String chosenWord = round.getCurrentWord();
        assertTrue(round.checkGuess(chosenWord));
        assertFalse(round.checkGuess(chosenWord + "wrong"));
    }

    @Test
    public void testEndRound() {
        round.startRound();
        round.endRound();
        assertNotNull(round.getEndTime());
        assertEquals("completed", round.getStatus());
        assertTrue(round.getScore() >= 0);
    }

    @Test
    public void testCalculateScore() {
        round.setStartTime(LocalDateTime.now().minusSeconds(5));
        round.setEndTime(LocalDateTime.now());
        assertEquals(5, round.calculateScore());
    }

    @Test
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


