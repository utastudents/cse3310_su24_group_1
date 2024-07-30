package uta.cse3310;

import java.util.List;
import java.util.Random;

public class Gameplay {
    private List<Player> players;
    private int currentPlayerIndex;
    private String[] wheelSections = {"$500", "$1000", "BANKRUPT", "LOSE TURN", "$2000", "FREE SPIN"};
    private char[] puzzleBoard;
    private String puzzleWord;
    private boolean[] revealedLetters;

    public Gameplay(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        initializeGame();
    }

    private void initializeGame() {
        // Initialize puzzle board and puzzle word
        puzzleWord = "EXAMPLE";  // You can choose or randomly select a word here
        puzzleBoard = new char[puzzleWord.length()];
        revealedLetters = new boolean[puzzleWord.length()];
        
        // Initially, no letters are revealed
        for (int i = 0; i < puzzleBoard.length; i++) {
            puzzleBoard[i] = '_';
            revealedLetters[i] = false;
        }
    }

    public void startGame() {
        boolean gameFinished = false;
        while (!gameFinished) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("Current player: " + currentPlayer.getPlayerName());

            String spinResult = spinWheel();
            System.out.println("Wheel result: " + spinResult);

            handleSpinResult(currentPlayer, spinResult);

            if (spinResult.equals("LOSE TURN")) {
                moveToNextPlayer();
                continue;
            }

            if (!spinResult.equals("BANKRUPT")) {
                boolean guessCorrect = playerGuess(currentPlayer);
                if (guessCorrect) {
                    updatePuzzleBoard(currentPlayer);
                    if (checkPuzzleSolved()) {
                        System.out.println(currentPlayer.getPlayerName() + " has solved the puzzle!");
                        gameFinished = true;
                        break;
                    }
                } else {
                    moveToNextPlayer();
                }
            } else {
                moveToNextPlayer();
            }
        }
        displayFinalScores();
    }

    private String spinWheel() {
        Random random = new Random();
        int index = random.nextInt(wheelSections.length);
        return wheelSections[index];
    }

    private void handleSpinResult(Player player, String spinResult) {
        switch (spinResult) {
            case "BANKRUPT":
                player.setScore(0); // Reset player's score
                System.out.println(player.getPlayerName() + " went BANKRUPT!");
                break;
            case "LOSE TURN":
                System.out.println(player.getPlayerName() + " lost their turn!");
                break;
            case "FREE SPIN":
                System.out.println(player.getPlayerName() + " got a FREE SPIN!");
                // Handle free spin logic if applicable
                break;
            default:
                int value = Integer.parseInt(spinResult.replace("$", ""));
                player.addScore(value); // Add value to player's score
                System.out.println(player.getPlayerName() + " earned " + value + " dollars.");
                break;
        }
    }

    private boolean playerGuess(Player player) {
        // Simulate player's letter guess (in a real game, get input from player)
        char guessedLetter = 'E';  // Example guessed letter
        boolean correctGuess = false;

        for (int i = 0; i < puzzleWord.length(); i++) {
            if (puzzleWord.charAt(i) == guessedLetter) {
                puzzleBoard[i] = guessedLetter;
                revealedLetters[i] = true;
                correctGuess = true;
            }
        }
        return correctGuess;
    }

    private void updatePuzzleBoard(Player player) {
        System.out.println("Puzzle board: ");
        for (char c : puzzleBoard) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private boolean checkPuzzleSolved() {
        for (boolean revealed : revealedLetters) {
            if (!revealed) {
                return false;
            }
        }
        return true;
    }

    private void moveToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private void displayFinalScores() {
        System.out.println("Final scores:");
        for (Player player : players) {
            System.out.println(player.getPlayerName() + ": " + player.getScore());
        }
    }
}
