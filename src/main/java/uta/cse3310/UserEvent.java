package uta.cse3310;

import java.util.List;

public class UserEvent {
    public int lobbyId;
    public int playerId;
    public String status;
    public String userGuess;
    public List<Integer> validLetters;
    public char charInput;

    public UserEvent() {

    }

    public UserEvent(int lobbyId, int playerId, String status, String userGuess, List<Integer> validLetters, char charInput) {
        this.lobbyId = lobbyId;
        this.playerId = playerId;
        this.status = status;
        this.userGuess = userGuess;
        this.validLetters = validLetters;
        this.charInput = charInput;
    }
}
