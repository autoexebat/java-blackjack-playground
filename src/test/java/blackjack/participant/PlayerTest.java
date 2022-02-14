package blackjack.participant;

import blackjack.card.*;
import blackjack.state.State;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTest {
    private List<Card> blackjackCards;
    private List<Card> allAceCards;
    private List<Card> bustCards;

    @BeforeEach
    void beforeEach() {
        blackjackCards = new ArrayList<>(Arrays.asList(new Card(CardType.CLUB, CardNumber.ACE), new Card(CardType.CLUB, CardNumber.K)));
        allAceCards = new ArrayList<>(Arrays.asList(new Card(CardType.CLUB, CardNumber.ACE), new Card(CardType.CLUB, CardNumber.ACE)));
        bustCards = new ArrayList<>(Arrays.asList(new Card(CardType.CLUB, CardNumber.NINE), new Card(CardType.CLUB, CardNumber.K), new Card(CardType.CLUB, CardNumber.J)));
    }

    @Test
    @DisplayName("플레이어 게임 시작 시 최초 동작에 대한 테스트 (카드 2장뽑기)")
    void startingAct() {
        PlayingCards playingCards = new PlayingCards();
        Player player = new Player("ydh", 5000);
        player.startingAct(playingCards);
        Cards playersCards = player.cards();

        Assertions.assertThat(playersCards.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("hit할 때  동작여부 확인")
    void hit() {
        PlayingCards playingCards = new PlayingCards();
        Player p1 = new Player("ydh", 5000);
        p1.changeCards(allAceCards);
        int beforeCardsCount = p1.cardsCount();

        p1.hit(playingCards);
        int afterCardCount = p1.cardsCount();

        Assertions.assertThat(p1.canPlay()).isEqualTo(true);
        Assertions.assertThat(beforeCardsCount).isEqualTo(afterCardCount - 1);
    }

    @Test
    @DisplayName("hit할 때 bust인 경우 동작여부 확인")
    void hitBust() {
        PlayingCards playingCards = new PlayingCards();
        Player p1 = new Player("ydh", 5000);
        p1.changeCards(bustCards);
        int beforeCardsCount = p1.cardsCount();

        p1.hit(playingCards);
        int afterCardCount = p1.cardsCount();

        Assertions.assertThat(p1.isBust()).isEqualTo(true);
        Assertions.assertThat(beforeCardsCount).isEqualTo(afterCardCount - 1);
    }

    @Test
    @DisplayName("플레이어가 블랙잭일 때 플레이어 최종금액 업데이트")
    void budgetWinByBlackjack() {
        int betting = 5000;
        Player p1 = new Player("ydh", betting);
        p1.updateState(State.blackjack());
        p1.updateBudget();

        Assertions.assertThat(p1.budget()).isEqualTo(betting * 1.5);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아닌 상태에서 승리한 경우")
    void budgetWinWithoutBlackjack() {
        int betting = 5000;
        Player p1 = new Player("ydh", betting);
        p1.updateState(State.win());
        p1.updateBudget();

        Assertions.assertThat(p1.budget()).isEqualTo(betting * 1);
    }

    @Test
    @DisplayName("플레이어와 딜러가 비겼을 경우")
    void budgetDraw() {
        int betting = 5000;
        Player p1 = new Player("ydh", betting);
        p1.updateState(State.draw());
        p1.updateBudget();

        Assertions.assertThat(p1.budget()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이가 가능한지 확인")
    void canPlay() {
        Player p1 = new Player("ydh", 5000);
        Player p2 = new Player("ydh", 5000);
        Player p3 = new Player("ydh", 5000);
        p1.changeCards(allAceCards);
        p2.changeCards(blackjackCards);
        p3.changeCards(bustCards);

        Assertions.assertThat(p1.canPlay()).isEqualTo(true);
        Assertions.assertThat(p2.canPlay()).isEqualTo(false);
        Assertions.assertThat(p3.canPlay()).isEqualTo(false);
    }

    @Test
    @DisplayName("블랙잭 확인")
    void blackjack() {
        Player p1 = new Player("ydh", 5000);
        Player p2 = new Player("ydh", 5000);
        Player p3 = new Player("ydh", 5000);
        p1.changeCards(blackjackCards);
        p2.changeCards(allAceCards);
        p3.changeCards(bustCards);

        Assertions.assertThat(p1.isBlackjack()).isEqualTo(true);
        Assertions.assertThat(p2.isBlackjack()).isEqualTo(false);
        Assertions.assertThat(p3.isBlackjack()).isEqualTo(false);
    }

    @Test
    @DisplayName("bust 확인")
    void bust() {
        Player p1 = new Player("ydh", 5000);
        Player p2 = new Player("ydh", 5000);
        Player p3 = new Player("ydh", 5000);
        p1.changeCards(bustCards);
        p2.changeCards(blackjackCards);
        p3.changeCards(allAceCards);

        Assertions.assertThat(p1.isBust()).isEqualTo(true);
        Assertions.assertThat(p2.isBust()).isEqualTo(false);
        Assertions.assertThat(p3.isBust()).isEqualTo(false);
    }
}
