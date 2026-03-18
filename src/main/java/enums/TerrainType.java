package enums;

public enum TerrainType {
    GRASS('.'),
    WATER('~'),
    SAND('≈'),
    ICE_MOUNTAIN('∆'),
    MOUNTAIN('^'),
    FOREST('♣'),
    LAVA('♨');

    private final char symbol;

    TerrainType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
