package SimonGame101;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {
    private ArrayList<Integer> topScores = new ArrayList<>();

    public void addScore(int score) {
        topScores.add(score);
        Collections.sort(topScores, Collections.reverseOrder()); // Highest first
        if (topScores.size() > 10) {
            topScores.remove(topScores.size() - 1); // Keep top 10 only
        }
    }

    public ArrayList<Integer> getTopScores() {
        return topScores;
    }

    public String topScoresString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < topScores.size(); i++) {
            sb.append((i + 1)).append(". ").append(topScores.get(i)).append("\n");
        }
        return sb.toString();
    }
}