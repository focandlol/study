package generic.test.ex6;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void draw(Deck deck) {
        hand.add(deck.draw());
    }

    public void showHand() {
        hand.sort(null);
        System.out.println(name + "의 카드: " + hand + ", 합계: " + sum());
    }

    public int sum(){
        int sum = 0;
        for (Card card : hand) {
            sum += card.getNum();
        }
        return sum;
    }

    public void getWinner(Player o){
        if(this.sum() > o.sum()){
            System.out.println(name + "승리");
        } else if(this.sum() < o.sum()){
            System.out.println(o.name + "승리");
        } else{
            System.out.println("무승부");
        }
    }
}
