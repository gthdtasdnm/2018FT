import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void addCard(Card c){
        this.hand.add(c);
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void removeCard(Card c){
        hand.remove(c);
    }

    public Card removeRandomCard() {
        //da sie karten bereits gemischt auf der hand sind entferne ich die erste
        Card c = hand.getFirst();
        hand.removeFirst();

        return c;
    }
}
