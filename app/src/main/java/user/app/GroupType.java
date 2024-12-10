package user.app;

public enum GroupType {
    TWO(2), MANY(3);

    private final int value;

    public int getValue() {
        return value;
    }

    private GroupType(int value) {
        this.value = value;
    }

    public static GroupType toGroupType(int value) {
        if (value == 2) {
            return TWO;
        } else if (value == 3) {
            return MANY;
        } else {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
}
