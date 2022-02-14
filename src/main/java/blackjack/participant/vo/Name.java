package blackjack.participant.vo;

public class Name {
    private String name;

    public Name(String name) {
        if(name.isEmpty() || name == null) {
            throw new IllegalArgumentException("이름을 제대로 써주세요.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
