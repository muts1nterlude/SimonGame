package test;

import SimonGame101.GameTimer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTimerTest {

    @Test
    void testTimerLogic() throws InterruptedException {
        // 500ms timer
        GameTimer timer = new GameTimer(500);
        timer.start();

        assertFalse(timer.isTimeUp(), "Timer should not be up yet");

        Thread.sleep(600); // Wait for expiration

        assertTrue(timer.isTimeUp(), "Timer should report time is up");
        assertEquals(0, timer.getRemainingSeconds(), "Remaining seconds should be 0 when finished");
    }
}