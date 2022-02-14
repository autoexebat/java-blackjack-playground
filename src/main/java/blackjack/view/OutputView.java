package blackjack.view;

import blackjack.participant.Dealer;
import blackjack.participant.Player;
import blackjack.participant.Players;

public class OutputView {
    public void gameResult(Dealer dealer, Players players) {
        System.out.println();
        System.out.println(dealer.name() + " : " + dealer.cards().showAllCards() + " -" + "결과 : " + dealer.cards().cardSum());
        for(Player p : players.getPlayers()) {
            System.out.println(p.name() + " : " + p.cards().showAllCards() + " -" + "결과 : " + p.cards().cardSum());
        }
        System.out.println("##최종 수익");
        for(Player p : players.getPlayers()) {
            System.out.println(p.name() + " : " + p.budget());
        }
    }
    public void dealerMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void showInitCards(Dealer dealer, Players players) {
        String names = players.playerNames();
        System.out.println("딜러와" + names + "에게 2장의 카드를 나누었습니다.");
        showDealerCards(dealer);
        showPlayersCards(players);
    }

    public void showPlayerCards(Player p) {
        System.out.println(p.name() + "의 카드결과 : " + p.cards().showAllCards());
    }

    private void showDealerCards(Dealer dealer) {
        System.out.println(dealer.name() + " : " + dealer.cards().showCards(1));
    }

    private void showPlayersCards(Players players) {
        for(Player p : players.getPlayers()) {
            showPlayerCards(p);
        }
    }

}
