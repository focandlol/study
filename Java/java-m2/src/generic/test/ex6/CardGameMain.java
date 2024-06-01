package generic.test.ex6;

import java.util.List;

public class CardGameMain {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");

        for (int i = 0; i < 5; i++) {
            player1.draw(deck);
            player2.draw(deck);
        }

        player1.showHand();
        player2.showHand();

        player1.getWinner(player2);


    }
}
