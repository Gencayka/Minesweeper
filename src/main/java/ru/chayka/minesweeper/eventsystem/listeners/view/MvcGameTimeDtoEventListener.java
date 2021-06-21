package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MvcGameTimeDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcGameTimeDtoEventListener
        extends MvcEventListener {
    void acceptEvent(MvcGameTimeDtoEvent event);
}
