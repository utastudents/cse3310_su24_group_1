package uta.cse3310;

import java.util.List;

public class UserEvent {
    public int lobbyId;
    public int playerId;
    public String status;
    public String userGuess;
    public List<Integer> validLetters1;
    public List<Integer> validLetters2;
    public List<Integer> validLetters3;
    public char charInput;

    public UserEvent() {

    }

    public UserEvent(int lobbyId, int playerId, String status, String userGuess, 
                     List<Integer> validLetters1, List<Integer> validLetters2, 
                     List<Integer> validLetters3,char charInput) {
        this.lobbyId = lobbyId;
        this.playerId = playerId;
        this.status = status;
        this.userGuess = userGuess;
        this.validLetters1 = validLetters1;
        this.validLetters2 = validLetters2;
        this.validLetters3 = validLetters3;
        this.charInput = charInput;
    }
}
