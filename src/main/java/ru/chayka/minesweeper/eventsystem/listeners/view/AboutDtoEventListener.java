package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.AboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface AboutDtoEventListener
        extends MinesweeperEventListener {
    void acceptEvent(AboutDtoEvent event);
}
