import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void testNormaleKarteErstellung() {
        Card katzeM = new Card(Tierart.KATZE, Geschlecht.MAENNLICH);
        assertEquals(Tierart.KATZE, katzeM.getTierart());
        assertEquals(Geschlecht.MAENNLICH, katzeM.getGeschlecht());
        assertEquals("Katze ♂", katzeM.toString());
    }

    @Test
    public void testGleicheKartenSindEqual() {
        Card c1 = new Card(Tierart.HUND, Geschlecht.WEIBLICH);
        Card c2 = new Card(Tierart.HUND, Geschlecht.WEIBLICH);
        assertEquals(c1, c2);
    }

    @Test
    public void testUnterschiedlicheKartenNichtEqual() {
        Card c1 = new Card(Tierart.HUND, Geschlecht.WEIBLICH);
        Card c2 = new Card(Tierart.HUND, Geschlecht.MAENNLICH);
        Card c3 = new Card(Tierart.KATZE, Geschlecht.WEIBLICH);

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
    }

    @Test
    public void testSchwarzerPeter() {
        Card peter = new Card(null, null);
        assertTrue(peter.istSchwarzerPeter());
        assertEquals("Peter", peter.toString());
    }

    @Test
    public void testUngültigeKarteNullGeschlecht() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Card(Tierart.KATZE, null);
        });
    }

    @Test
    public void testUngültigeKartePeterMitGeschlecht() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Card(null, Geschlecht.MAENNLICH);
        });
    }

    @Test
    public void testCardEquals() {
        Card c1 = new Card(Tierart.BÄR,Geschlecht.WEIBLICH);
        Card c2 = new Card(Tierart.BÄR,Geschlecht.MAENNLICH);
        assertEquals(c1, c2); // sollte true sein
    }

}
