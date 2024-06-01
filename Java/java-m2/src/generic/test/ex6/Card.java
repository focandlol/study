package generic.test.ex6;

public class Card implements Comparable<Card>{

    private int num;
    private Suit suit;

    public Card(int num, Suit suit) {
        this.num = num;
        this.suit = suit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public int compareTo(Card o) {
        if (this.num != o.num) {
            return Integer.compare(this.num, o.num);
        } else {
            return this.suit.compareTo(o.suit);
        }
    }

    @Override
    public String toString() {
        return
                num +
                "(" + suit.getName() +
                ')';
    }
}
