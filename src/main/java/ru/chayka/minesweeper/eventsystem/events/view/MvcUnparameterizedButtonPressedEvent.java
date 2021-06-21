package ru.chayka.minesweeper.eventsystem.events.view;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;
import ru.chayka.minesweeper.view.UnparameterizedButton;

public final class MvcUnparameterizedButtonPressedEvent
        implements MvcEvent {
    private final UnparameterizedButton button;

    public MvcUnparameterizedButtonPressedEvent(UnparameterizedButton button) {
        this.button = button;
    }

    public UnparameterizedButton getButton() {
        return button;
    }
}
