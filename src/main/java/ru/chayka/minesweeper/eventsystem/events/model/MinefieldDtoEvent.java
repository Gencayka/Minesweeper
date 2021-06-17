package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;

public final class MinefieldDtoEvent
        implements MinesweeperEvent {
    private final int numOfRows;
    private final int numOfColumns;

    public MinefieldDtoEvent(int numOfRows, int numOfColumns) {
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
