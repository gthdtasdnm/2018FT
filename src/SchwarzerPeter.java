import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SchwarzerPeter implements Iterable<Player>{
    private List<Player> players = new ArrayList<>();
    List<Card> deck;

    public SchwarzerPeter(){
        this.deck = deck();
        Collections.shuffle(deck);

    }

    public void addPlayer(String name){
        players.add(new Player(name));
    }


    public void dealCards() {
        while (!deck.isEmpty()) {
            for (Player p : players) {
                if (deck.isEmpty()) break; // falls ungerade Verteilung
                Card c = deck.removeFirst();   // entfernt und gibt erste Karte zurück
                p.addCard(c);
            }
        }
    }

    private int currentPlayerIndex = 0;

    public Player head(){

        return players.get(currentPlayerIndex);
    }

    public List<Card> deck(){
        List<Card> deck = CardFactory.alleKartenErzeugen();
        return deck;
    }

    public void nextTurn(){
        if (players.isEmpty()) return;
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }


    @Override
    public Iterator<Player> iterator() {
        //gibt immer den nächsten aktiven spieler in einer Kreis Struktur;
        List<Player> valid = new ArrayList<>();

        // Spieler mit Karten in originaler Reihenfolge sammeln
        for (Player p : players) {
            if (!p.getHand().isEmpty()) {
                valid.add(p);
            }
        }

        // Kein Spieler mehr aktiv? Leerer Iterator
        if (valid.isEmpty()) {
            return Collections.emptyIterator();
        }

        // Aktiver Spieler ist der erste in players mit Karten
        Player active = null;
        for (Player p : players) {
            if (!p.getHand().isEmpty()) {
                active = p;
                break;
            }
        }

        // Finde Index des aktiven Spielers in valid
        int index = valid.indexOf(active);

        // Rotierte Liste erzeugen: [active, ..., letzter, erster, ..., vor active]
        List<Player> rotated = new ArrayList<>();
        rotated.addAll(valid.subList(index, valid.size()));
        rotated.addAll(valid.subList(0, index));

        return rotated.iterator();
    }

    public List<Player> getPlayerList() {
        return players;
    }

    public void removePlayer(Player p){
        players.remove(p);
    }
}
