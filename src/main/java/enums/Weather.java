package enums;

public enum Weather {
    HEAT("H"),
    RAIN("R"),
    SANDSTORM("S"),
    SCORCHING_CLIMATE("S"),
    SNOW("S"),
    THUNDERSTORM("T");

    private String symbol;

    Weather(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }
}
