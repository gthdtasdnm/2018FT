import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FullGameTest {

    @Test
    public void testGameEndsWithOnePlayerLeft() {
        SchwarzerPeter game = new SchwarzerPeter();
        game.addPlayer("A");
        game.addPlayer("B");
        game.addPlayer("C");

        game.dealCards();
        SchwarzerPeterAutomaton automaton = new SchwarzerPeterAutomaton(game);

        for (Player p : game) {
            for (Card c1 : p.getHand()) {
                for (Card c2 : p.getHand()) {
                    if (!c1.equals(c2) && c1.equals(c2)) {
                        automaton.discard(p, c1, c2);
                    }
                }
            }
            automaton.ready(p);
        }

        while (!automaton.isFinished()) {
            Player active = game.head();
            automaton.select(active);

            for (Card c : active.getHand()) {
                if (automaton.validMatchingCard(active, c)) {
                    automaton.matching(active, c);
                    break;
                }
            }

            if (game.getPlayerList().equals(active)) {
                automaton.noMatch(active);
            }
        }

        assertEquals(1, game.getPlayerList().size());
    }
}
