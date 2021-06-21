package ru.chayka.minesweeper.eventsystem.listeners.controller;

import ru.chayka.minesweeper.eventsystem.events.view.MvcDifficultyModeButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcDifficultyModeButtonPressedEventListener
        extends MvcEventListener {
    void acceptEvent(MvcDifficultyModeButtonPressedEvent event);
}
