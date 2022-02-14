package blackjack.participant;

import blackjack.card.*;
import blackjack.state.Bust;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class DealerTest {
    @Test
    @DisplayName("카드게임 시작 시 딜러가 카드2장 받는지 테스트")
    void dealerStart() {
        PlayingCards playingCards = new PlayingCards();
        Dealer dealer = new Dealer();
        dealer.startingAct(playingCards);

        Cards dealerCards = dealer.cards();
        List<Card> cardList = dealerCards.getCards();

        Assertions.assertThat(cardList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러 카드 합이 16이하면 카드를 더 뽑는지 테스트")
    void hit() {
        Card c1 = new Card(CardType.CLUB, CardNumber.TEN);
        Card c2 = new Card(CardType.CLUB, CardNumber.NINE);
        Card c3 = new Card(CardType.CLUB, CardNumber.EIGHT);
        Cards cards = new Cards();
        cards.addCards(Arrays.asList(c1, c2, c3));

        PlayingCards playingCards = new PlayingCards();
        Dealer dealer = new Dealer();
        dealer.changeCards(cards);
        dealer.hit(playingCards);

        Assertions.assertThat(dealer.shouldPickMoreCard()).isEqualTo(false);
        Assertions.assertThat(dealer.state().status()).isExactlyInstanceOf(Bust.class);
    }
}
