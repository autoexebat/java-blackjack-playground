package blackjack.card;

import blackjack.card.Card;
import blackjack.card.CardNumber;
import blackjack.card.CardType;
import blackjack.card.PlayingCards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayingCardsTest {
    @Test
    @DisplayName("카드덱 준비. 카드타입, 카드번호가 다 준비 되어 있는지 테스트")
    void playingCards() {
        PlayingCards playingCards = new PlayingCards();
        List<Card> cards = playingCards.getCards();

        Set<CardType> allCardTypes = cards.stream().map(c -> c.getCardType())
                .collect(Collectors.toSet());

        Set<CardNumber> allCardNumbers = cards.stream().map(c -> c.getCardNumber())
                .collect(Collectors.toSet());

        Assertions.assertThat(allCardTypes.size()).isEqualTo(CardType.values().length);
        Assertions.assertThat(allCardNumbers.size()).isEqualTo(CardNumber.values().length);
        Arrays.stream(CardType.values()).forEach(cardType -> Assertions.assertThat(allCardTypes).contains(cardType));
        Arrays.stream(CardNumber.values()).forEach(cardNumbers -> Assertions.assertThat(allCardNumbers).contains(cardNumbers));
    }

    @ParameterizedTest
    @DisplayName("카드덱에서 카드 뽑기 테스트")
    @ValueSource(ints = {1,2,3,4,5})
    void pickCards(int numberOfCards) {
        PlayingCards playingCards = new PlayingCards();
        int originalCardSize = playingCards.getCards().size();
        List<Card> pickCards = playingCards.pickCards(numberOfCards);
        int leftSize = playingCards.getCards().size();

        Assertions.assertThat(originalCardSize).isEqualTo(leftSize + numberOfCards);
        Assertions.assertThat(numberOfCards).isEqualTo(pickCards.size());
    }
}
