package ru.chayka.minesweeper.eventsystem.listeners.controller;

import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface UnparameterizedButtonPressedEventListener
        extends MinesweeperEventListener {
    void acceptEvent(UnparameterizedButtonPressedEvent event);
}
