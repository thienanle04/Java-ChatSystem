package user.app;

public enum MessageType {
    SEND(1), RECEIVE(2);

    private final int value;

    public int getValue() {
        return value;
    }

    private MessageType(int value) {
        this.value = value;
    }

    public static MessageType toMessageType(int value) {
        if (value == 1) {
            return RECEIVE;
        } else if (value == 2) {
            return SEND;
        } else {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
}
