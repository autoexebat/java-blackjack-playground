package blackjack.participant;

import blackjack.card.Cards;
import blackjack.card.PlayingCards;
import blackjack.state.State;

public class Dealer implements Participant {
    private final static int CARD_SUM_OFFSET = 16;
    private String name = "Dealer";
    private State state;
    private Cards cards;

    public Dealer() {
        this.cards = new Cards();
        this.state = State.hit();
    }

    @Override
    public void startingAct(PlayingCards playingCards) {
        cards.addCards(playingCards.pickCards(2));
        if(cards.isBlackjack()) {
            this.state = State.blackjack();
        }
    }

    public void hit(PlayingCards playingCards) {
        this.cards.addCards(playingCards.pickCards(1));
        if(cards.isBust()) {
            this.state = State.bust();
        }
    }

    public State state() {
        return this.state;
    }

    public Cards cards() {
        return cards;
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public String name() {
        return this.name;
    }

    public boolean shouldPickMoreCard() {
        return cards.cardSum() <= CARD_SUM_OFFSET;
    }

    public void changeCards(Cards cards) {
        this.cards.changeCards(cards.getCards());
    }
}
