package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PlayerTest extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PlayerTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(PlayerTest.class);
    }

    public void testPlayerInitialization() {
        Player player = new Player("John Doe", 1, "1");

        assertEquals("John Doe", player.getPlayerName());
        assertEquals(1, player.getPlayerID());
        assertEquals(0, player.getPoints());
        assertFalse(player.getStatus()); // Updated to reflect getStatus
        assertEquals("", player.getInventory()); // Updated to reflect empty inventory
    }

    public void testSetPlayerName() {
        Player player = new Player("John Doe", 1, "1");
        player.setPlayerName("Jane Doe");

        assertEquals("Jane Doe", player.getPlayerName());
    }

    public void testSetPlayerID() {
        Player player = new Player("John Doe", 1, "1");
        player.setPlayerID(2);

        assertEquals(2, player.getPlayerID());
    }

    public void testSetPoints() {
        Player player = new Player("John Doe", 1, "1");
        player.setPoints(100);
        assertEquals(100, player.getPoints());
    }

    public void testSetStatusPlayer() {
        Player player = new Player("John Doe", 1, "1");
        player.setStatusPlayer(true); // Updated to reflect setStatusPlayer

        assertTrue(player.getStatus()); // Updated to reflect getStatus
    }

    public void testSetInventory() {
        Player player = new Player("John Doe", 1, "1");
        player.setInventory("sword");

        assertEquals("sword", player.getInventory());
    }
}
