package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MinefieldCellDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface MinefieldCellDtoEventListener
        extends MinesweeperEventListener {
    void acceptEvent(MinefieldCellDtoEvent event);
}
