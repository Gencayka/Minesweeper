package ru.chayka.minesweeper.eventsystem.events.view;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;

public final class DifficultyModeButtonPressedEvent
        implements MinesweeperEvent {
    private final String strDifficultyMode;

    public DifficultyModeButtonPressedEvent(String strDifficultyMode) {
        this.strDifficultyMode = strDifficultyMode;
    }

    public String getStrDifficultyMode() {
        return strDifficultyMode;
    }
}
