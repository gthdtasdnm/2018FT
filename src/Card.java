public class Card {
    private Tierart tierart;
    private Geschlecht geschlecht;

    public Card(Tierart tierart, Geschlecht geschlecht) {
        //Schwarzer Peter
        if (tierart == null) {
            if (geschlecht != null) {
                throw new IllegalArgumentException("Der Schwarze Peter darf kein Geschlecht haben.");
            }
        } else {
            //Kein Schwarzer Peter
            if (geschlecht == null) {
                throw new IllegalArgumentException("Normale Karten müssen ein Geschlecht haben.");
            }
        }

        this.tierart = tierart;
        this.geschlecht = geschlecht;
    }



    public Tierart getTierart() {
        return tierart;
    }

    public Geschlecht getGeschlecht() {
        return geschlecht;
    }

    @Override
    public String toString() {
        //Schwarzer Peter
        if (tierart == null) {
            return "Peter";
        }
        return tierart.toString() + " " + geschlecht.getSymbol();
    }

    @Override
    public boolean equals(Object obj) {
        //Test is Card
        if (!(obj instanceof Card)) return false;

        //Convert to Card, test equal
        Card c = (Card) obj;
        return this.tierart == c.getTierart() &&
               this.geschlecht == c.getGeschlecht();
    }

    @Override
    public int hashCode() {
        return 31 * tierart.hashCode() + (geschlecht == null ? 0 : geschlecht.hashCode());
    }

    public boolean istSchwarzerPeter() {
        if(tierart == null && geschlecht == null){
            return true;
        }else{
            return false;
        }
    }
}
