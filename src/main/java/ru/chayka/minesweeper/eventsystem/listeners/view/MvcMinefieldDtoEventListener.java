package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcMinefieldDtoEventListener
        extends MvcEventListener {
    void acceptEvent(MvcMinefieldDtoEvent event);
}
