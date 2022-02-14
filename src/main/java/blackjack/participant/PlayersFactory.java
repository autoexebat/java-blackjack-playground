package blackjack.participant;

import java.util.ArrayList;
import java.util.List;

public class PlayersFactory {

    public static Players create(List<String> playerList, List<Integer> bettingList) {
        if(playerList.size() != bettingList.size()) {
            throw new IllegalArgumentException("플레이어 정보가 제대로 입력되지 않았습니다");
        }
        int playerSize = playerList.size();
        List<Player> players = new ArrayList<>();
        for(int i=0; i<playerSize; i++) {
            players.add(new Player(playerList.get(i), bettingList.get(i)));
        }
        return new Players(players);
    }
}
