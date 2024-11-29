package projetJavaEnsea;

public class GameTimer {
    private long startTime;
    private boolean running;

    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        }
        return 0;
    }

    public void stop() {
        running = false;
    }
}