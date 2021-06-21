package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MvcRecordNewLeaderEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcRecordNewLeaderEventListener
        extends MvcEventListener {
    void acceptEvent(MvcRecordNewLeaderEvent event);
}
