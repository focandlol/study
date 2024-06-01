package generic.test.ex6;

public enum Suit {

    SPADE("♠","s1"), // 스페이드(♠)
    HEART("♥","s2"),// 하트(♥)
    DIAMOND("♦","s3"),// 다이아몬드(♦)
    CLUB("♣","s4");// 클로버(♣)
    private String name;
    private String icon;

    Suit(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
