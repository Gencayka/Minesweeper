package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.MvcLeaderboardDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

public interface MvcLeaderboardDtoEventListener
        extends MvcEventListener {
    void acceptEvent(MvcLeaderboardDtoEvent event);
}
