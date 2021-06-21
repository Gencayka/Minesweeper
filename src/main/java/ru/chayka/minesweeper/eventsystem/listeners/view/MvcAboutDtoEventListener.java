package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MvcAboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcAboutDtoEventListener
        extends MvcEventListener {
    void acceptEvent(MvcAboutDtoEvent event);
}
