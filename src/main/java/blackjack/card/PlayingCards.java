package blackjack.card;

import java.util.*;
import java.util.stream.Collectors;

public class PlayingCards {
    private final List<Card> cards;
    private Random random;

    public PlayingCards() {
        cards = new ArrayList<>();
        random = new Random();
        cards.addAll(create(CardType.CLUB));
        cards.addAll(create(CardType.DIAMOND));
        cards.addAll(create(CardType.HEART));
        cards.addAll(create(CardType.SPADE));
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> pickCards(int number) {
        List<Card> pickedCards = new ArrayList<>();
        int size = cards.size();
        for(int i=0; i<number; i++) {
            int picked = random.nextInt(size);
            pickedCards.add(this.cards.get(picked));
            this.cards.remove(picked);
        }
        return pickedCards;
    }


    private List<CardNumber> getAllCardNumber() {
        return Arrays.stream(CardNumber.values()).collect(Collectors.toList());
    }

    private List<Card> create(CardType cardType) {
        List<Card> cards = new ArrayList<>();
        List<CardNumber> allCardNumber = getAllCardNumber();
        for(CardNumber number : allCardNumber) {
            cards.add(new Card(cardType, number));
        }
        return cards;
    }
}
