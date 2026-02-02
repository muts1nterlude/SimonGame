package test;

import SimonGame101.SimonGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SequenceIntegrationTest {

    @Test
    void testSequenceAdvancement() {
        // Warning: This will initialize the GUI due to the constructor
        SimonGame game = new SimonGame();

        int initialSize = game.getSequence().size();
        assertEquals(1, initialSize, "Game should start with 1 color");

        // Get the required color and simulate correct input
        String correctColor = game.getSequence().get(0);
        game.handlePlayerInput(correctColor);

        // Verify the game state updated and added a new color
        assertEquals(2, game.getSequence().size(), "Sequence should grow after correct input");
    }
}