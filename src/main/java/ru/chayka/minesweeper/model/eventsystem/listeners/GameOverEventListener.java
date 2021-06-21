package ru.chayka.minesweeper.model.eventsystem.listeners;

import ru.chayka.minesweeper.model.eventsystem.events.GameOverEvent;

public interface GameOverEventListener
        extends MinesweeperModelEventListener {
    void acceptEvent(GameOverEvent event);
}
