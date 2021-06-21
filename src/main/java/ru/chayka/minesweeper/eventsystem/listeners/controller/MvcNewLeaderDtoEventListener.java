package ru.chayka.minesweeper.eventsystem.listeners.controller;

import ru.chayka.minesweeper.eventsystem.events.view.MvcNewLeaderDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcNewLeaderDtoEventListener
        extends MvcEventListener {
    void acceptEvent(MvcNewLeaderDtoEvent event);
}
