package blackjack;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager(new InputView(), new OutputView());
        gameManager.play();
    }
}
