package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.FlagCounterDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface FlagCounterDtoEventListener
        extends MinesweeperEventListener {
    void acceptEvent(FlagCounterDtoEvent event);
}
