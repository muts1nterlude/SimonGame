package SimonGame101;

import java.util.ArrayList;
import java.util.Random;

public class SimonGame {

    private ArrayList<String> sequence;
    private ArrayList<String> playerInput;
    private int score;

    private final String[] colors = {"Red", "Blue", "Green", "Yellow"};

    private final Random random;
    private final ScoreManager scoreManager;
    private final GameUI gui;
    private final GameTimer timer;

    public SimonGame() {
        sequence = new ArrayList<>();
        playerInput = new ArrayList<>();
        random = new Random();
        scoreManager = new ScoreManager();

        // 2-minute timer (120,000 ms)
        timer = new GameTimer(2 * 60 * 1000);

        gui = new GameUI(this);
        startGame();
    }

    public void startGame() {
        sequence.clear();
        playerInput.clear();
        score = 0;

        timer.start();

        gui.updateScore(score);
        gui.updateStatus("Watch the sequence...");
        gui.showMessage("Welcome to Simon Game! You have 2 minutes.");

        addColorToSequence();
        gui.playSequence(sequence);
    }

    private void addColorToSequence() {
        sequence.add(colors[random.nextInt(colors.length)]);
    }

    public void handlePlayerInput(String color) {

        // ⏱ Check if time is up
        if (timer.isTimeUp()) {
            gui.updateStatus("Time's up!");
            gameOver();
            return;
        }

        playerInput.add(color);
        int index = playerInput.size() - 1;

        // Wrong input
        if (!playerInput.get(index).equals(sequence.get(index))) {
            gui.updateStatus("Wrong input!");
            gameOver();
            return;
        }

        // Correct full sequence
        if (playerInput.size() == sequence.size()) {
            score++;
            gui.updateScore(score);
            gui.updateStatus("Correct! Next round...");

            playerInput.clear();
            addColorToSequence();

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            gui.playSequence(sequence);
        }
    }

    private void gameOver() {
        scoreManager.addScore(score);

        gui.updateTopScores(scoreManager.topScoresString());
        gui.showMessage("Game Over! Final Score: " + score);

        startGame();
    }

    // 🔍 Getter for testing
    public ArrayList<String> getSequence() {
        return sequence;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public static void main(String[] args) {
        new SimonGame();
    }
}
