import java.util.Collections;
import java.util.LinkedList;

public class CardStuck {

    static LinkedList<Card> deck;

    //デッキ作成メソッド
    public static void createDeck() {

        LinkedList<Card> NewDeck = new LinkedList<Card>();

        // listに値を入れる。この段階では昇順
        for(int i = 0 ; i < 13 ; i++) {
            for (int j = 0; j< 4; j++){
                Card card = new Card(i,j);
                NewDeck.add(card);
            }
        }
        // シャッフルして、順番を変える
        Collections.shuffle(NewDeck);
        deck = NewDeck;
    }

    //デッキからカードを1枚取得するメソッド
    public static Card drawCard() {
        Card card = deck.poll();
        return card;
    }

    public static LinkedList<Card> getDeck() {
        return deck;
    }


}
