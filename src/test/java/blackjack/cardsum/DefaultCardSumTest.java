package blackjack.cardsum;

import blackjack.card.Card;
import blackjack.card.CardNumber;
import blackjack.card.CardType;
import blackjack.cardsum.CardSum;
import blackjack.cardsum.DefaultCardSum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultCardSumTest {
    @Test
    @DisplayName("카드숫자 합")
    void cardSum() {
        Card c1 = new Card(CardType.CLUB, CardNumber.TEN);
        Card c2 = new Card(CardType.CLUB, CardNumber.NINE);
        Card c3 = new Card(CardType.CLUB, CardNumber.EIGHT);

        List<Card> cards = new ArrayList<>(Arrays.asList(c1, c2, c3));
        CardSum cardSum = new DefaultCardSum();
        int score = cardSum.score(cards);

        Assertions.assertThat(score).isEqualTo(27);
    }

    @Test
    @DisplayName("ACE가 11로 적용되는 21과 가장 가까운 카드숫자 합")
    void cardSumWithAce11() {
        Card c1 = new Card(CardType.CLUB, CardNumber.ACE);
        Card c2 = new Card(CardType.CLUB, CardNumber.K);

        List<Card> cards = new ArrayList<>(Arrays.asList(c1, c2));
        CardSum cardSum = new DefaultCardSum();
        int score = cardSum.score(cards);

        Assertions.assertThat(score).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE가 1로 적용되는 21과 가장 가까운 카드숫자 합")
    void cardSumWithAce1() {
        Card c1 = new Card(CardType.CLUB, CardNumber.ACE);
        Card c2 = new Card(CardType.CLUB, CardNumber.SEVEN);
        Card c3 = new Card(CardType.DIAMOND, CardNumber.ACE);

        List<Card> cards = new ArrayList<>(Arrays.asList(c1, c2, c3));
        CardSum cardSum = new DefaultCardSum();
        int score = cardSum.score(cards);

        Assertions.assertThat(score).isEqualTo(19);
    }
}
