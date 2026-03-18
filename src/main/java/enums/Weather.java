package enums;

public enum Weather {

    CLEAR("☁"),
    HEAT("☀"),
    RAIN("\uD83C\uDF27"),
    SANDSTORM("⛆"),
    SCORCHING_CLIMATE("S"),
    SNOW("❅"),
    THUNDERSTORM("⚡");

    private String symbol;

    Weather(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }
}
