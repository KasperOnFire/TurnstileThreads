package server;

public class TurnstileCounter {

    volatile long count = 0;

    public synchronized long getCount() {
        return count;
    }

    public synchronized void incr() {
        count++;
    }
}
