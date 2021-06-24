package ru.chayka.minesweeper.view.settings;

public enum DefaultSetting {
    DEFAULT_LEADER_NAME("default_leader_name", "Anon");

    private final String key;
    private final String defaultValue;

    DefaultSetting(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
