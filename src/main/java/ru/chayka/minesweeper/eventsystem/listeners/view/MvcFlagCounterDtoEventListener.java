package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MvcFlagCounterDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcFlagCounterDtoEventListener
        extends MvcEventListener {
    void acceptEvent(MvcFlagCounterDtoEvent event);
}
