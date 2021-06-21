package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcMinefieldDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcMinefieldDtoEventSender
        extends MvcEventSender<MvcMinefieldDtoEventListener, MvcMinefieldDtoEvent> {
    @Override
    protected void notifyOneListener(MvcMinefieldDtoEventListener listener, MvcMinefieldDtoEvent event) {
        listener.acceptEvent(event);
    }
}
