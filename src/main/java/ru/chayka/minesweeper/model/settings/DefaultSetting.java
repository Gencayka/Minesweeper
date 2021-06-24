package ru.chayka.minesweeper.model.settings;

public enum DefaultSetting {
    GAME_TIME_MAX("game_time_max", "999"),
    LEADERBOARD_XML_FILE_NAME("leaderboard_xml_file_name", "./leaderboard.xml"),
    DEFAULT_LEADER_NAME("default_leader_name", "Anon"),
    ABOUT_TEXT("about_text", "Here could be your advertising");

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
