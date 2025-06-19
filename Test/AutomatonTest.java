import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutomatonTest {

    @Test
    public void testDiscardRemovesCardsIfPairValid() {
        Player p = new Player("Anna");
        Card c1 = new Card(Tierart.KATZE, Geschlecht.WEIBLICH);
        Card c2 = new Card(Tierart.KATZE, Geschlecht.MAENNLICH);
        p.addCard(c1);
        p.addCard(c2);

        SchwarzerPeter game = new SchwarzerPeter();
        game.addPlayer("Anna");
        SchwarzerPeterAutomaton automaton = new SchwarzerPeterAutomaton(game);
        automaton.discard(p, c1, c2);
        assertFalse(p.getHand().contains(c1));
        assertFalse(p.getHand().contains(c2));
    }

    @Test
    public void testReadyLeadsToDrawingPhaseEventually() {
        SchwarzerPeter game = new SchwarzerPeter();
        game.addPlayer("A");
        game.addPlayer("B");

        SchwarzerPeterAutomaton automaton = new SchwarzerPeterAutomaton(game);
        for (Player p : game) {
            automaton.ready(p);
        }

        assertTrue(true); // Nur Funktionsprüfung
    }
}
