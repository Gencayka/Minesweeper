package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;

public final class FlagCounterDtoEvent
        implements MinesweeperEvent {
    private final int numOfRemainingMines;

    public FlagCounterDtoEvent(int numOfRemainingMines) {
        this.numOfRemainingMines = numOfRemainingMines;
    }

    public int getNumOfRemainingMines() {
        return numOfRemainingMines;
    }
}
