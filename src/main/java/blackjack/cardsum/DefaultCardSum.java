package blackjack.cardsum;

import blackjack.card.Card;
import blackjack.card.CardNumber;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultCardSum implements CardSum {
    private final static int MAX_SCORE = 21;
    private final static int ACE_REPLACE_SCORE = 11;

    @Override
    public int score(List<Card> cards) {
        if(cards.isEmpty()) {
            return 0;
        }
        int countOfAce = countOfAce(cards);
        int sum = minimumCardScore(cards);

        for(int i=0; i<countOfAce; i++) {
            sum = replaceAceSum(sum);
        }
        return sum;
    }

    private int replaceAceSum(int sum) {
        if(sum + (ACE_REPLACE_SCORE - 1) <= MAX_SCORE) {
            sum += (ACE_REPLACE_SCORE - 1);
        }
        return sum;
    }

    private int countOfAce(List<Card> cards) {
        return cards.stream().map(Card::getCardNumber)
                .filter(CardNumber::isAce)
                .collect(Collectors.toList())
                .size();
    }

    private int minimumCardScore(List<Card> cards) {
        return cards.stream()
                .map(Card::getCardNumber)
                .map(CardNumber::getScore)
                .reduce((c1, c2) -> c1 + c2)
                .get();
    }
}
