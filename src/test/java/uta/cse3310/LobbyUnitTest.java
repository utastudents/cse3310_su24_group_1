package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LobbyUnitTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LobbyUnitTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(LobbyUnitTest.class);
    }

    public void testLobby() {
        Lobby L = new Lobby();

        // test whenever there is only 1 player
        Player p1 = new Player("JohnDoe", 1);
        L.players.add(p1);

        L.gameStart();
        L.displayPlayers();
        L.setPlayerCount();

        assertEquals(1, L.getPlayerCount());

        // test with a second player added in
        Player p2 = new Player("JaneDoe", 2);
        L.players.add(p2);

        L.gameStart();
        L.displayPlayers();
        L.setPlayerCount();

        assertEquals(2, L.getPlayerCount());

        // test leaderboard after 2 players are added
        L.displayLeaderboard();
    }
}