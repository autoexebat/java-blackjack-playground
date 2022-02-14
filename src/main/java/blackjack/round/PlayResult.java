package blackjack.round;

import blackjack.participant.Dealer;
import blackjack.participant.Player;
import blackjack.participant.Players;
import blackjack.state.State;

import java.util.List;

public class PlayResult {

    public void gameRound(Dealer dealer, Players players) {
        List<Player> playerList = players.getPlayers();
        for(Player p : playerList) {
            p.updateState(playersResultState(dealer, p));
            p.updateBudget();
        }
    }

    private State playersResultState(Dealer dealer, Player player) {
        if(player.isBust()) {
            return State.bust();
        }
        int dealerCardSum = dealer.cards().cardSum();
        int playerCardSum = player.cards().cardSum();
        if(dealerCardSum > playerCardSum) {
            return State.bust();
        }
        return State.win();
    }
}
