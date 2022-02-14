package blackjack.card;

import blackjack.card.Card;
import blackjack.card.CardNumber;
import blackjack.card.CardType;
import blackjack.card.Cards;
import blackjack.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardsTest {
    private static List<Card> cardList;
    private static List<Card> blackjackList;

    @BeforeEach
    void before() {
        Card c1 = new Card(CardType.CLUB, CardNumber.TEN);
        Card c2 = new Card(CardType.CLUB, CardNumber.NINE);
        Card c3 = new Card(CardType.CLUB, CardNumber.EIGHT);
        cardList = new ArrayList<>(Arrays.asList(c1, c2, c3));

        Card c4 = new Card(CardType.CLUB, CardNumber.ACE);
        Card c5 = new Card(CardType.CLUB, CardNumber.Q);
        blackjackList = new ArrayList<>(Arrays.asList(c4, c5));
    }

    @Test
    @DisplayName("카드총합에 대한 테스트")
    void cardSum() {
        Player player = new Player("p1", 1000);
        player.changeCards(cardList);
        Assertions.assertThat(player.cards().cardSum()).isEqualTo(27);

    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCards() {
        Cards cards = new Cards();
        cards.addCards(cardList);
        Assertions.assertThat(cards.cardsCount()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void showCards(int numberOfCards) {
        Cards cards = new Cards();
        cards.addCards(cardList);
        String allCards = cards.showCards(numberOfCards);
        String[] allCardsSplit = allCards.split(",");

        Assertions.assertThat(allCardsSplit.length).isEqualTo(numberOfCards);
    }

    @Test
    @DisplayName("블랙잭 확인")
    void isBlackjack() {
        Cards cards = new Cards();
        cards.addCards(blackjackList);
        Assertions.assertThat(cards.isBlackjack()).isEqualTo(true);
    }

    @Test
    @DisplayName("bust 확인")
    void isBust() {
        Cards cards = new Cards();
        cards.addCards(cardList);
        Assertions.assertThat(cards.isBust()).isEqualTo(true);
    }
}
