package blackjack.participant;

import blackjack.participant.vo.BettingMoney;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerFactoryTest {
    @Test
    @DisplayName("각각 리스트로 받은 플레이어 정보로 플레이어 객체 생성")
    void create() {
        List<String> names = Arrays.asList("abc", "bcd", "cde");
        List<Integer> betting = Arrays.asList(5000, 1000, 500);

        Players players = PlayersFactory.create(names, betting);
        List<String> playerNames = players.getPlayers().stream().map(p -> p.name()).collect(Collectors.toList());
        List<BettingMoney> bettingMoneyList = players.getPlayers().stream().map(p -> p.bettingMoney()).collect(Collectors.toList());

        Assertions.assertThat(players.playersCount()).isEqualTo(names.size());

        for(Player player : players.getPlayers()) {
            Assertions.assertThat(names).containsExactly("abc", "bcd", "cde");
            Assertions.assertThat(betting).containsExactly(5000, 1000, 500);
        }
    }
}
