package test;

import SimonGame101.ScoreManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class ScoreManagerTest {

    @Test
    void testScoreSortingAndLimit() {
        ScoreManager sm = new ScoreManager();
        // Add 12 scores
        for (int i = 1; i <= 12; i++) {
            sm.addScore(i);
        }

        ArrayList<Integer> top = sm.getTopScores();

        // Check limit
        assertEquals(10, top.size(), "Should only keep top 10 scores");
        // Check sorting (First should be 12, last should be 3)
        assertEquals(12, top.get(0), "Highest score should be first");
        assertEquals(3, top.get(9), "10th score should be 3");
    }
}
