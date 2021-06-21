package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;

public final class MvcMinefieldDtoEvent
        implements MvcEvent {
    private final int numOfRows;
    private final int numOfColumns;

    public MvcMinefieldDtoEvent(int numOfRows, int numOfColumns) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }
}
