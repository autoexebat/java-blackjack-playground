package blackjack.state;

public class Win implements Status{
    @Override
    public double rate() {
        return 1;
    }
}
