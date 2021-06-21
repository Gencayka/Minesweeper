package ru.chayka.minesweeper.eventsystem.listeners.controller;

import ru.chayka.minesweeper.eventsystem.events.view.MvcMinefieldButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcMinefieldButtonPressedEventListener
        extends MvcEventListener {
    void acceptEvent(MvcMinefieldButtonPressedEvent event);
}
