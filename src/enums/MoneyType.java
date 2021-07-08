package enums;

public enum MoneyType {

    LEU_100(100, 50),
    LEU_50(50, 50),
    LEU_10(10, 100),
    LEU_5(5, 100),
    LEU_1(1, 100);

    private final int value;
    private final int initialCount;


    MoneyType(final int newValue, final int count ) {

        this.value = newValue;
        this.initialCount = count;
    }

    public int getValue() { return value; }
    public int getInitialCount() { return initialCount; }
}
