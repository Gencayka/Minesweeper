package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcLeaderboardDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcLeaderboardDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcLeaderboardDtoEventSender
        extends MvcEventSender<MvcLeaderboardDtoEventListener, MvcLeaderboardDtoEvent> {
    @Override
    protected void notifyOneListener(MvcLeaderboardDtoEventListener listener, MvcLeaderboardDtoEvent event) {
        listener.acceptEvent(event);
    }
}
