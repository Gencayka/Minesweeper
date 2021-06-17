package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.GameTimeDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface GameTimeDtoEventListener
        extends MinesweeperEventListener {
    void acceptEvent(GameTimeDtoEvent event);
}
