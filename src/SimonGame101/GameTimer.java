package SimonGame101;

public class GameTimer {
    private final long durationMillis;
    private long startTime;

    public GameTimer(long durationMillis) {
        this.durationMillis = durationMillis;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public boolean isTimeUp() {
        return System.currentTimeMillis() - startTime >= durationMillis;
    }

    public long getRemainingSeconds() {
        long remaining = durationMillis - (System.currentTimeMillis() - startTime);
        return Math.max(0, remaining / 1000);
    }
}
