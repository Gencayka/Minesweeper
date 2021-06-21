package ru.chayka.minesweeper.model.eventsystem.listeners;

import ru.chayka.minesweeper.model.eventsystem.events.MinefieldDtoEvent;

public interface MinefieldDtoEventListener
        extends MinesweeperModelEventListener {
    void acceptEvent(MinefieldDtoEvent event);
}
