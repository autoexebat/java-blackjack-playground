package blackjack.cardsum;

import blackjack.card.Card;

import java.util.List;

public interface CardSum {
    final static int MAX_SCORE = 21;
    int score(List<Card> cards);
}
