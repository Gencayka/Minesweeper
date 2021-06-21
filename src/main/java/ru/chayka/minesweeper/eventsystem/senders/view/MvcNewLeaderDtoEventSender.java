package ru.chayka.minesweeper.eventsystem.senders.view;

import ru.chayka.minesweeper.eventsystem.events.view.MvcNewLeaderDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MvcNewLeaderDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcNewLeaderDtoEventSender
        extends MvcEventSender<MvcNewLeaderDtoEventListener, MvcNewLeaderDtoEvent> {
    @Override
    protected void notifyOneListener(MvcNewLeaderDtoEventListener listener, MvcNewLeaderDtoEvent event) {
        listener.acceptEvent(event);
    }
}
