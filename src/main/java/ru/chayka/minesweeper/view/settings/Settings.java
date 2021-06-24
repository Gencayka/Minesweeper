package ru.chayka.minesweeper.view.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.MvcBuilder;
import ru.chayka.minesweeper.PropertiesFileParser;

import java.util.Properties;

public class Settings {
    public static final String PROPERTIES_FILE_NAME = "data.properties";
    private static final Logger log = LoggerFactory.getLogger(Settings.class.getName());
    private final static Settings uniqueInstance = new Settings();

    private final String defaultLeaderName;

    private Settings() {
        Properties properties;
        try {
            properties = PropertiesFileParser.parse(PROPERTIES_FILE_NAME, MvcBuilder.class);
        } catch (Exception ex) {
            log.warn(createParseFailLogMessage());

            defaultLeaderName = getStringDefaultValue(DefaultSetting.DEFAULT_LEADER_NAME);
            return;
        }

        defaultLeaderName = tryToGetStringProperty(DefaultSetting.DEFAULT_LEADER_NAME, properties);
    }

    public static Settings getInstance() {
        return uniqueInstance;
    }

    public String getDefaultLeaderName() {
        return defaultLeaderName;
    }

    private String tryToGetStringProperty(DefaultSetting setting, Properties properties) {
        String parsedProperty = properties.getProperty(setting.getKey());
        if (parsedProperty == null || parsedProperty.isBlank()) {
            log.warn(createInvalidPropertyLogMessage(setting, properties));
            return getStringDefaultValue(setting);
        } else {
            return parsedProperty;
        }
    }

    private int tryToGetIntProperty(DefaultSetting setting, Properties properties) {
        try {
            int parsedProperty = Integer.parseInt(
                    properties.getProperty(setting.getKey()));
            if (parsedProperty < 0) {
                throw new NumberFormatException();
            } else {
                return parsedProperty;
            }
        } catch (NumberFormatException ex) {
            log.warn(createInvalidPropertyLogMessage(setting, properties));
            return getIntDefaultValue(setting);
        }
    }

    private String getStringDefaultValue(DefaultSetting setting) {
        return setting.getDefaultValue();
    }

    private int getIntDefaultValue(DefaultSetting setting) {
        try {
            return Integer.parseInt(setting.getDefaultValue());
        } catch (NumberFormatException ex) {
            log.error("Default value for {} property is invalid: {}",
                    setting.getKey(),
                    setting.getDefaultValue());
            throw ex;
        }
    }

    private String createParseFailLogMessage() {
        StringBuilder errorMessage = new StringBuilder(50);
        errorMessage.append("Failed to parse Properties file, default values are used:");
        for (ru.chayka.minesweeper.model.settings.DefaultSetting setting : ru.chayka.minesweeper.model.settings.DefaultSetting.values()) {
            errorMessage.append(System.lineSeparator());
            errorMessage.append(String.format(
                    "%s: %s", setting.getKey(), setting.getDefaultValue()));

        }
        return errorMessage.toString();
    }

    private String createInvalidPropertyLogMessage(DefaultSetting setting, Properties properties) {
        return String.format("%s property is invalid: %s. Default value is used: %s",
                setting.getKey(),
                properties.getProperty(setting.getKey()),
                setting.getDefaultValue());
    }
}
