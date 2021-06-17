package ru.chayka.minesweeper.eventsystem.events.view;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;
import ru.chayka.minesweeper.view.UnparameterizedButton;

public final class UnparameterizedButtonPressedEvent
        implements MinesweeperEvent {
    private final UnparameterizedButton button;

    public UnparameterizedButtonPressedEvent(UnparameterizedButton button) {
        this.button = button;
    }

    public UnparameterizedButton getButton() {
        return button;
    }
}
