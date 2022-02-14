package blackjack.participant;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.card.PlayingCards;
import blackjack.participant.vo.BettingMoney;
import blackjack.participant.vo.Name;
import blackjack.state.State;

import java.util.List;

public class Player implements Participant {
    private Name name;
    private double budget;
    private BettingMoney betting;
    private Cards cards;
    private State state;

    public Player(String name, int betting) {
        this.name = new Name(name);
        this.betting = new BettingMoney(betting);
        this.cards = new Cards();
        this.state = State.hit();
    }

    @Override
    public void startingAct(PlayingCards playingCards) {
        this.cards.addCards(playingCards.pickCards(2));
    }

    public double budget() {
        return budget;
    }

    public Cards cards() {
        return cards;
    }

    public boolean canPlay() {
        return cards.isBust() == false && cards.isBlackjack() == false;
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public void hit(PlayingCards playingCards) {
        this.cards.addCards(playingCards.pickCards(1));
        if(cards.isBust()) {
            this.state = State.bust();
        }
    }

    public void updateBudget() {
        budget += betting.getBetting() * state.getRate();
    }

    public void updateState(State state) {
        this.state = state;
    }

    public String name() {
        return this.name.getName();
    }

    public void changeCards(List<Card> cards) {
        this.cards.changeCards(cards);
    }

    public int cardsCount() {
        return cards.cardsCount();
    }

    public BettingMoney bettingMoney() {
        return this.betting;
    }
}
