package test;

import SimonGame101.SimonGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameOverIntegrationTest {

    @Test
    void testWrongInputTriggersReset() {
        SimonGame game = new SimonGame();

        // Find a color that is NOT the correct one
        String correctColor = game.getSequence().get(0);
        String wrongColor = correctColor.equals("Red") ? "Blue" : "Red";

        // Provide wrong input
        game.handlePlayerInput(wrongColor);

        // The game should have called gameOver() and restarted (size 1)
        // Note: In a real environment, you'd check if the score was saved,
        // but since scoreManager is private, we check the sequence reset.
        assertEquals(1, game.getSequence().size(), "Game should reset to 1 color after Game Over");
    }
}
