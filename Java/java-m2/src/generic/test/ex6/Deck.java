package generic.test.ex6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> deck = new ArrayList<Card>();

    public Deck() {
        for(int i=1; i<=13; i++) {
            for(Suit suit : Suit.values()) {
               // System.out.println("suit = " + suit);
               // System.out.println("suit = " + suit.getName());
                deck.add(new Card(i,suit));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card draw(){
        return deck.remove(0);
    }

}
