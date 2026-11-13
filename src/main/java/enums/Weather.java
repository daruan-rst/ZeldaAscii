package enums;

public enum Weather {

    CLEAR("☁"),
    HEAT("☀"),
    RAIN("☂"),
    SANDSTORM("≈"),
    SCORCHING_CLIMATE("!"),
    SNOW("*"),
    THUNDERSTORM("⚡");

    private String symbol;

    Weather(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }
}
