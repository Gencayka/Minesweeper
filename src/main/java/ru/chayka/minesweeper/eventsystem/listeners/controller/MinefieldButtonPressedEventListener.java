package ru.chayka.minesweeper.eventsystem.listeners.controller;

import ru.chayka.minesweeper.eventsystem.events.view.MinefieldButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface MinefieldButtonPressedEventListener
        extends MinesweeperEventListener {
    void acceptEvent(MinefieldButtonPressedEvent event);
}
