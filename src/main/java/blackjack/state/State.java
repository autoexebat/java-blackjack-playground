package blackjack.state;

public class State {
    private Status status;

    private State(Status status) {
        this.status = status;
    }

    public double getRate() {
        return status.rate();
    }

    public Status status() {
        return status;
    }

    public static State blackjack() {
        return new State(new Blackjack());
    }
    public static State draw() {
        return new State(new Draw());
    }
    public static State win() {
        return new State(new Win());
    }
    public static State bust() {
        return new State(new Bust());
    }
    public static State hit() {
        return new State(new Hit());
    }
}
