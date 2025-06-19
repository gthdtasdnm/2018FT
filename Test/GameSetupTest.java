import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameSetupTest {

    @Test
    public void testAddPlayer() {
        SchwarzerPeter game = new SchwarzerPeter();
        game.addPlayer("Alice");
        assertEquals(1, game.getPlayerList().size());
    }

    @Test
    public void testDeckHasExpectedNumberOfCards() {
        SchwarzerPeter game = new SchwarzerPeter();
        int expected = CardFactory.alleKartenErzeugen().size(); // Kartenanzahl prüfen
        assertEquals(expected, game.deck().size());
    }

    @Test
    public void testDealCardsDistributesAllCards() {
        SchwarzerPeter game = new SchwarzerPeter();
        game.addPlayer("A");
        game.addPlayer("B");
        game.addPlayer("C");
        game.dealCards();
        int totalCards = game.getPlayerList().stream().mapToInt(p -> p.getHand().size()).sum();
        assertEquals(game.deck().size(), totalCards); // ggf. anpassen
    }
}
