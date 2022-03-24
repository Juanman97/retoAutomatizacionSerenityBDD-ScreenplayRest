package co.com.sofka.util;

public enum UpdateTitleKey {
    TITLE("[title]");

    private final String value;

    UpdateTitleKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
