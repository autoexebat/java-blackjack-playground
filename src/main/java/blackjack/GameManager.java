package blackjack;

import blackjack.card.PlayingCards;
import blackjack.participant.Dealer;
import blackjack.participant.Player;
import blackjack.participant.Players;
import blackjack.participant.PlayersFactory;
import blackjack.round.FirstResult;
import blackjack.round.PlayResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class GameManager {
    private InputView inputView;
    private OutputView outputView;

    public GameManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        // 게임 준비
        Players players = readyPlayers();
        Dealer dealer = new Dealer();
        PlayingCards playingCards = new PlayingCards();
        // 처음 카드 분배
        gamePlayerCardsInit(dealer, players, playingCards);
        outputView.showInitCards(dealer, players);
        // 첫 카드 결과
        FirstResult firstResult = new FirstResult();
        firstResult.gameRound(dealer, players);
        // 게임 진행
        playRound(players, playingCards); // 플레이어들 차례대로 플레이
        dealerPlayStart(dealer, playingCards); // 딜러 진행
        // 카드 게임 결과
        PlayResult playResult = new PlayResult();
        playResult.gameRound(dealer, players);
        outputView.gameResult(dealer, players);
    }

    private Players readyPlayers() {
        List<String> playerList = inputView.inputPlayers();
        List<Integer> bettingList = inputView.inputBetting(playerList);
        return PlayersFactory.create(playerList, bettingList);
    }

    private void dealerPlayStart(Dealer dealer, PlayingCards playingCards) {
        while(dealer.shouldPickMoreCard()) {
            outputView.dealerMoreCard();
            dealer.hit(playingCards);
        }
    }

    private void gamePlayerCardsInit(Dealer dealer, Players players, PlayingCards playingCards) {
        dealer.startingAct(playingCards);
        players.getPlayers().forEach(p -> p.startingAct(playingCards));
    }

    private void playRound(Players players, PlayingCards playingCards) {
        List<Player> playerList = players.getPlayers();
        for(Player p : playerList) {
            play(playingCards, p);
            outputView.showPlayerCards(p);
        }
    }

    private void play(PlayingCards playingCards, Player player) {
        String yn = inputView.moreCardYn(true, player.name());
        while("y".equals(yn) && player.canPlay()) {
            player.hit(playingCards);
            outputView.showPlayerCards(player);
            yn = inputView.moreCardYn(player.canPlay(), player.name());
        }
    }
}
