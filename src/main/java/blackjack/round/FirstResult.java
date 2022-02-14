package blackjack.round;

import blackjack.participant.Dealer;
import blackjack.participant.Player;
import blackjack.participant.Players;
import blackjack.state.State;

public class FirstResult {

    public void gameRound(Dealer dealer, Players players) {
        for(Player p : players.getPlayers()) {
            playerStartingActState(dealer, p);
        }
    }

    private void playerStartingActState(Dealer dealer, Player player) {
        State earnCase = playerEarnMoneyCase(dealer, player);
        if (earnCase != null) {
            player.updateState(earnCase);
            return;
        }
        if (dealer.isBlackjack()) {
            player.updateState(State.bust());
            return;
        }
        player.updateState(State.hit());
    }

    private State playerEarnMoneyCase(Dealer dealer, Player player) {
        if(dealer.isBlackjack() && player.isBlackjack()) {
            return State.draw();
        }
        if(player.isBlackjack()) {
            return State.blackjack();
        }
        if(dealer.isBust()) {
            return State.win();
        }
        return null;
    }
}
