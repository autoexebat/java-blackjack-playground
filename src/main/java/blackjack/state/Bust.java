package blackjack.state;


public class Bust implements Status{
    @Override
    public double rate() {
        return -1;
    }
}
