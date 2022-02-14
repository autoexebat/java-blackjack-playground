package blackjack.participant.vo;

public class BettingMoney {
    private int betting;

    public BettingMoney(int betting) {
        if(betting < 1) {
            throw new IllegalArgumentException("베팅금액을 제대로 써주세요");
        }
        this.betting = betting;
    }

    public int getBetting() {
        return betting;
    }
}
