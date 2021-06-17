package ru.chayka.minesweeper.eventsystem.listeners.controller;

import ru.chayka.minesweeper.eventsystem.events.view.DifficultyModeButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface DifficultyModeButtonPressedEventListener
        extends MinesweeperEventListener {
    void acceptEvent(DifficultyModeButtonPressedEvent event);
}
