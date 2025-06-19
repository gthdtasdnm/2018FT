import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CardFactory {

    public static List<Card> alleKartenErzeugen() {
        List<Card> karten = new ArrayList<>();

        for (Tierart tier : Tierart.values()) {
            // Nur Schwarzer Peter wird ignoriert (wenn du Tierart.PETER nicht verwendest)
            if (tier != null) {
                karten.add(new Card(tier, Geschlecht.MAENNLICH));
                karten.add(new Card(tier, Geschlecht.WEIBLICH));
            }
        }

        // Schwarzer Peter (Tierart = null, Geschlecht = null)
        karten.add(new Card(null, null));

        return karten;
    }
}
