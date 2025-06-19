import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SchwarzerPeterAutomaton {



    private abstract class State{
        abstract void entry();
        abstract void discard(Player p, Card x, Card y);
        abstract void matching(Player p, Card x);
        abstract void ready(Player p);
        abstract void noMatch(Player p);
        abstract void select(Player p);
    }

    private final State PaareFinden = new State(){

        @Override
        void entry() {
            for(Player p:game){
                notReady.add(p);
            }
            System.out.println("Paare Finden");
            removeAllPairs();
        }

        @Override
        void discard(Player p, Card x, Card y) {
            System.out.println("Pärchen abgeworfen");
            p.removeCard(x);
            p.removeCard(y);
            setState(this);
        }

        @Override
        void matching(Player p, Card x) {

        }

        @Override
        void ready(Player p) {
            setReady(p);
            System.out.println(p + " ist bereit");
            if(allReady()){
                System.out.println("Jeder ist bereit");
                setState(KarteZiehen);
            }
        }

        @Override
        void noMatch(Player p) {

        }

        @Override
        void select(Player p) {

        }
    };

    private final State KarteZiehen = new State(){

        @Override
        void entry() {
            System.out.println("Karte Ziehen");
            // Wenn nur noch ein Spieler übrig ist → Spielende
            if (game.getPlayerList().size() == 1) {
                setState(Spielende);
                return;
            }

        }

        @Override
        void discard(Player p, Card x, Card y) {

        }

        @Override
        void matching(Player p, Card x) {

        }

        @Override
        void ready(Player p) {

        }

        @Override
        void noMatch(Player p) {

        }

        @Override
        void select(Player p) {
            if (p.getHand().isEmpty()) {
                System.out.println("Spieler " + p.getName() + " hat keine Karten mehr auf der Hand");
                removeIfEmpty(p);
                return;
            }
            Player left = getLeftNeighbor(p);
            card = left.removeRandomCard();

            System.out.println(p.getName() + " zieht Karte von " + left.getName());
            System.out.println(card);

            // Check nach dem Ziehen
            removeIfEmpty(left);
            removeIfEmpty(p);
            setState(KarteGezogen);

        }


    };

    private final State Spielende = new State(){

        @Override
        void entry() {
            System.out.println("Spiel wurde beendet");
            System.out.println(game.getPlayerList().getFirst() + " hat gewonnen");
        }

        @Override
        void discard(Player p, Card x, Card y) {

        }

        @Override
        void matching(Player p, Card x) {

        }

        @Override
        void ready(Player p) {

        }

        @Override
        void noMatch(Player p) {

        }

        @Override
        void select(Player p) {

        }
    };

    private final State KarteGezogen = new State(){

        @Override
        void entry() {

            System.out.println("Karte Gezogen");
            for(Card c: game.head().getHand()){
                matching(game.head(),c);
            }
            noMatch(game.head());

        }

        @Override
        void discard(Player p, Card x, Card y) {}

        @Override
        void matching(Player p, Card x) {
            if(!(game.head()==p)){return;}
            if(!(validMatchingCard(p,x))){return;}
            p.removeCard(x);
            setState(KarteZiehen);
        }

        @Override
        void ready(Player p) {}

        @Override
        void noMatch(Player p) {
            if(!(game.head()==p)){return;}
            p.addCard(card);
            game.nextTurn();
            setState(KarteZiehen);
        }

        @Override
        void select(Player p) {}
    };

    private final SchwarzerPeter game;

    public SchwarzerPeterAutomaton(SchwarzerPeter game){
        this.game = game;
        init();
    }

    public void init(){
        state = setState(PaareFinden);
    }
    
    private State state;

    private Card card;

    private List<Player> notReady = new ArrayList<>();



    private void setReady(Player p){

        notReady.remove(p);
    }

    private boolean allReady(){
        return notReady.isEmpty();
    }

    public boolean validMatchingCard(Player p, Card x){
        return p.getHand().contains(x) && x.equals(card);
    }

    private void removeIfEmpty(Player p) {
        if(p.getHand().size()==0){
            game.removePlayer(p);
        }
    }

    private boolean validPair(Player p, Card x, Card y){
        return p.getHand().contains(x) &&
               p.getHand().contains(y) &&
               !x.equals(y) &&
               x.getTierart() == y.getTierart();
    }

    public Player getLeftNeighbor(Player p) {
        // Liste der Spieler mit Karten
        List<Player> activePlayers = game.getPlayerList().stream()
                                            .filter(player -> !player.getHand().isEmpty())
                                            .collect(Collectors.toList());

        // Prüfen: Spieler p muss in der Liste enthalten sein
        int index = activePlayers.indexOf(p);
        if (index == -1) {
            throw new IllegalArgumentException("Player not in active player list");
        }

        // Index des linken Nachbarn im Kreis berechnen
        int nextIndex = (index + 1) % activePlayers.size();
        return activePlayers.get(nextIndex);
    }

    private State setState(State s) {
        if (this.state == s) return state; //keine Doppel-Initialisierung
        this.state = s;
        s.entry();
        return s;
    }

    public boolean isFinished() {return state==Spielende;}

    public void removeAllPairs() {
        for (Player p : game) {
            List<Card> hand = new ArrayList<>(p.getHand());
            Set<Integer> usedIndices = new HashSet<>();

            for (int i = 0; i < hand.size(); i++) {
                if (usedIndices.contains(i)) continue;

                Card c1 = hand.get(i);

                for (int j = i + 1; j < hand.size(); j++) {
                    if (usedIndices.contains(j)) continue;

                    Card c2 = hand.get(j);

                    if (c1.equals(c2)) {
                        discard(p, c1, c2);
                        usedIndices.add(i);
                        usedIndices.add(j);
                        break; // nächstes i
                    }
                }
            }
        }
    }

    public void nextTurn() {
        game.nextTurn();
    }




    public void entry(){state.entry();}
    void discard(Player p, Card x, Card y){state.discard(p,x,y);}
    void ready(Player p){state.ready(p);}
    void select(Player p){state.select(p);}
    void matching(Player p, Card x){state.matching(p,x);}
    void noMatch(Player p){state.noMatch(p);}
}
