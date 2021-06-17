package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;

public final class RecordNewLeaderEvent
        implements MinesweeperEvent {
    private final String difficulty;

    public RecordNewLeaderEvent(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
