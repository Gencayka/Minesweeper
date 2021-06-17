package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.DifficultyModesDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface DifficultyModesDtoEventListener
        extends MinesweeperEventListener {
    void acceptEvent(DifficultyModesDtoEvent event);
}
