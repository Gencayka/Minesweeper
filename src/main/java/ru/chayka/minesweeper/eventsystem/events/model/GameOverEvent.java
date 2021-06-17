package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;

public final class GameOverEvent
        implements MinesweeperEvent {
    private final boolean isWon;

    public GameOverEvent(boolean isWon) {
        this.isWon = isWon;
    }

    public boolean isWon() {
        return isWon;
    }
}
