package blackjack.card;

public class Card {
    private CardType cardType;
    private CardNumber cardNumber;

    public Card(CardType cardType, CardNumber cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public String showCard() {
        String number = String.valueOf(cardNumber.getScore());
        if("1".equals(number)) {
            number = "ACE";
        }
        return number + cardType.getType();
    }
}
