package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;

public final class MvcGameOverEvent
        implements MvcEvent {
    private final boolean isWon;

    public MvcGameOverEvent(boolean isWon) {
        this.isWon = isWon;
    }

    public boolean isWon() {
        return isWon;
    }
}
