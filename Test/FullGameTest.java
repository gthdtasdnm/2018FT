import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FullGameTest {

    @Test
    public void testGameEndsWithOnePlayerLeft() {
        SchwarzerPeter game = new SchwarzerPeter();
        game.addPlayer("A");
        game.addPlayer("B");
        game.addPlayer("C");
        game.addPlayer("D");
        game.addPlayer("E");

        game.dealCards();
        SchwarzerPeterAutomaton automaton = new SchwarzerPeterAutomaton(game);

        // Anfangspaare ablegen
        for (Player p : game) {
            boolean[] discarded = new boolean[p.getHand().size()]; // Index-Merker für bereits abgelegte Karten
            for (int i = 0; i < p.getHand().size(); i++) {
                if (discarded[i]) continue;
                Card c1 = p.getHand().get(i);
                for (int j = i + 1; j < p.getHand().size(); j++) {
                    if (discarded[j]) continue;
                    Card c2 = p.getHand().get(j);
                    if (c1.equals(c2)) {
                        automaton.discard(p, c1, c2);
                        discarded[i] = true;
                        discarded[j] = true;
                        break;
                    }
                }
            }
            automaton.ready(p);
        }

        // Spielverlauf
        int i = 1;
        while (!automaton.isFinished() && i < 1000) {
            Player active = game.head();
            automaton.select(active);


            boolean matched = false;
            for (Card c : active.getHand()) {
                if (automaton.validMatchingCard(active, c)) {
                    automaton.matching(active, c);
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                automaton.noMatch(active);
            }

            System.out.println("Runde: " + i + " | Spieler: " + active.getName());
            System.out.println(active.getHand());
            i++;
        }

        System.out.println("Spiel beendet nach " + i + " Runden.");
        assertEquals(1, game.getPlayerList().size(), "Am Ende sollte genau ein Spieler übrig bleiben.");
    }
}
