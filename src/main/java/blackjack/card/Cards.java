package blackjack.card;

import blackjack.cardsum.CardSum;
import blackjack.cardsum.DefaultCardSum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cards {
    private static final int MAX_CARD_SUM = 21;
    private List<Card> cards;
    private CardSum cardSum;

    public Cards() {
        this.cards = new ArrayList<>();
        this.cardSum = new DefaultCardSum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int cardSum() {
        return cardSum.score(this.cards);
    }

    public void addCards(List<Card> cards) {
        cards.forEach(this::addCard);
    }

    public int cardsCount() {
        return cards.size();
    }

    public boolean isBlackjack() {
        return cardSum() == MAX_CARD_SUM;
    }

    public boolean isBust() {
        return cardSum() > MAX_CARD_SUM;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String showCards(int numberOfCard) {
        return IntStream.range(0, numberOfCard)
                .mapToObj(number -> cards.get(number))
                .map(Card::showCard)
                .collect(Collectors.joining(","));
    }

    public String showAllCards() {
        return cards.stream()
                .map(Card::showCard)
                .collect(Collectors.joining(","));
    }

    public void changeCards(List<Card> cardList) {
        this.cards = cardList;
    }
}
