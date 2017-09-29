package server;

public class Turnstile extends Thread {

    private final TurnstileCounter counter;

    public Turnstile(TurnstileCounter counter) {
        this.counter = counter;
    }

    public void run() {
        counter.incr();
    }

}
