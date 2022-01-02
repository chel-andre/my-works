package enums;

public enum Attribute {
    VALUE("value");

    private String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}