package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private Scanner sc = new Scanner(System.in);

    public List<String> inputPlayers() {
        List<String> players = new ArrayList<>();
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String strPlayers = sc.nextLine();
        for(String player : strPlayers.split(",")) {
            players.add(player);
        }
        return players;
    }

    public List<Integer> inputBetting(List<String> names) {
        List<Integer> bettingList = new ArrayList<>();
        for(String name : names) {
            System.out.println(name + "의 배팅 금액은?");
            bettingList.add(Integer.parseInt(sc.nextLine()));
        }
        return bettingList;
    }

    public String moreCardYn(boolean canPlay, String name) {
        if(canPlay) {
            System.out.println(name + ", 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            return sc.nextLine();
        }
        return "n";
    }
}
