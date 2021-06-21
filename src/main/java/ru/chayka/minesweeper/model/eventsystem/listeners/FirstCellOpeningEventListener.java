package ru.chayka.minesweeper.model.eventsystem.listeners;

import ru.chayka.minesweeper.model.eventsystem.events.FirstCellOpeningEvent;

public interface FirstCellOpeningEventListener
        extends MinesweeperModelEventListener {
    void acceptEvent(FirstCellOpeningEvent event);
}
