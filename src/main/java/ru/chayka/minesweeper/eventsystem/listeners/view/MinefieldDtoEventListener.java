package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MinefieldDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface MinefieldDtoEventListener
        extends MinesweeperEventListener {
    void acceptEvent(MinefieldDtoEvent event);
}
