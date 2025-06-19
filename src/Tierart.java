public enum Tierart {
    KATZE,
    HUND,
    MAUS,
    AFFE,
    SCHWEIN,
    FROSCH,
    KUH,
    PINGUIN,
    LÖWE,
    ZIEGE,
    BÄR,
    ENTE,
    PFERD; // Das ist der Schwarze Peter – kommt nur einmal vor!

    public boolean istPeter() {
        return this == null;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "Peter";
        }
        // Großschreibung mit erstem Buchstaben groß (optional)
        String name = name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
