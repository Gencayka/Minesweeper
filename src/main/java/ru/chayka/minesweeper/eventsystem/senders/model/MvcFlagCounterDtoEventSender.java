package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcFlagCounterDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcFlagCounterDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcFlagCounterDtoEventSender
        extends MvcEventSender<MvcFlagCounterDtoEventListener, MvcFlagCounterDtoEvent> {
    @Override
    protected void notifyOneListener(MvcFlagCounterDtoEventListener listener, MvcFlagCounterDtoEvent event) {
        listener.acceptEvent(event);
    }
}
