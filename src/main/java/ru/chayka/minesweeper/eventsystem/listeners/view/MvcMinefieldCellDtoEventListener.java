package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldCellDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcMinefieldCellDtoEventListener
        extends MvcEventListener {
    void acceptEvent(MvcMinefieldCellDtoEvent event);
}
