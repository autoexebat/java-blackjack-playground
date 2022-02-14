package blackjack.participant;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String playerNames() {
        return players.stream().map(p -> p.name())
                .collect(Collectors.joining(","));
    }

    public int playersCount() {
        return players.size();
    }
}
