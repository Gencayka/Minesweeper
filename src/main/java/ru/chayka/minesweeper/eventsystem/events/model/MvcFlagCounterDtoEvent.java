package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;

public final class MvcFlagCounterDtoEvent
        implements MvcEvent {
    private final int numOfRemainingMines;

    public MvcFlagCounterDtoEvent(int numOfRemainingMines) {
        this.numOfRemainingMines = numOfRemainingMines;
    }

    public int getNumOfRemainingMines() {
        return numOfRemainingMines;
    }
}
