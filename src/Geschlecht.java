public enum Geschlecht {
    MAENNLICH("♂"),
    WEIBLICH("♀");

    private final String symbol;

    Geschlecht(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
