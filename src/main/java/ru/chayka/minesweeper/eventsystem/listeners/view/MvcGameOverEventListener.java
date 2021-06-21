package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MvcGameOverEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcGameOverEventListener
        extends MvcEventListener {
    void acceptEvent(MvcGameOverEvent event);
}
