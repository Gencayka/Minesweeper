package ru.chayka.minesweeper.eventsystem.listeners.controller;

import ru.chayka.minesweeper.eventsystem.events.view.MvcUnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcUnparameterizedButtonPressedEventListener
        extends MvcEventListener {
    void acceptEvent(MvcUnparameterizedButtonPressedEvent event);
}
