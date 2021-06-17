package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.GameOverEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface GameOverEventListener
        extends MinesweeperEventListener {
    void acceptEvent(GameOverEvent event);
}
