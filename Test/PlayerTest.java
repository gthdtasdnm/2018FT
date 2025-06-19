import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class PlayerTest {

    @Test
    public void testAddAndRemoveCard() {
        Player p = new Player("Anna");
        Card c = new Card(Tierart.HUND, Geschlecht.WEIBLICH);
        p.addCard(c);
        assertTrue(p.getHand().contains(c));
        p.removeCard(c);
        assertFalse(p.getHand().contains(c));
    }

    @Test
    public void testRemoveRandomCardReducesHandSize() {
        Player p = new Player("Max");
        p.addCard(new Card(Tierart.HUND, Geschlecht.MAENNLICH));
        p.addCard(new Card(Tierart.HUND, Geschlecht.WEIBLICH));
        int before = p.getHand().size();
        Card drawn = p.removeRandomCard();
        assertNotNull(drawn);
        assertEquals(before - 1, p.getHand().size());
    }

    @Test
    public void testHasCard() {
        Player p = new Player("Lina");
        Card c = new Card(Tierart.PINGUIN, Geschlecht.MAENNLICH);
        p.addCard(c);
        assertTrue(p.getHand().contains(c));
    }
}
