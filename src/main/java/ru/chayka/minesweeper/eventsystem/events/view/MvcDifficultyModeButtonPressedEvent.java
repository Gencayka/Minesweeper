package ru.chayka.minesweeper.eventsystem.events.view;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;

public final class MvcDifficultyModeButtonPressedEvent
        implements MvcEvent {
    private final String strDifficultyMode;

    public MvcDifficultyModeButtonPressedEvent(String strDifficultyMode) {
        this.strDifficultyMode = strDifficultyMode;
    }

    public String getStrDifficultyMode() {
        return strDifficultyMode;
    }
}
