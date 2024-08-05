package uta.cse3310;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player player = new Player("John Doe", 1);

        assertEquals("John Doe", player.getPlayerName());
        assertEquals(1, player.getPlayerID());
        assertEquals(0, player.getPoints());
        assertFalse(player.getStatus()); // Updated to reflect getStatus
        assertEquals("", player.getInventory()); // Updated to reflect empty inventory
    }

    @Test
    public void testSetPlayerName() {
        Player player = new Player("John Doe", 1);
        player.setPlayerName("Jane Doe");

        assertEquals("Jane Doe", player.getPlayerName());
    }

    @Test
    public void testSetPlayerID() {
        Player player = new Player("John Doe", 1);
        player.setPlayerID(2);

        assertEquals(2, player.getPlayerID());
    }

    @Test
    public void testSetPoints() {
        Player player = new Player("John Doe", 1);
        player.setPoints(100);

        assertEquals(100, player.getPoints());
    }

    @Test
    public void testSetStatusPlayer() {
        Player player = new Player("John Doe", 1);
        player.setStatusPlayer(true); // Updated to reflect setStatusPlayer

        assertTrue(player.getStatus()); // Updated to reflect getStatus
    }

    @Test
    public void testSetInventory() {
        Player player = new Player("John Doe", 1);
        player.setInventory("sword");

        assertEquals("sword", player.getInventory());
    }
}
