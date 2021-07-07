package enums;

public enum StartMoney {

        NR_LEU_1(100),
        NR_LEU_5(100),
        NR_LEU_10(100),
        NR_LEU_50(50),
        NR_LEU_100(50);

        private final int value;

        StartMoney(final int newValue) {
            value = newValue;
        }
        public int getValue() { return value; }

}
