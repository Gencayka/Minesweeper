package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;

public final class MvcRecordNewLeaderEvent
        implements MvcEvent {
    private final String difficulty;

    public MvcRecordNewLeaderEvent(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
