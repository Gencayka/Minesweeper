package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldCellDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcMinefieldCellDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcMinefieldCellDtoEventSender
        extends MvcEventSender<MvcMinefieldCellDtoEventListener, MvcMinefieldCellDtoEvent> {
    @Override
    protected void notifyOneListener(MvcMinefieldCellDtoEventListener listener, MvcMinefieldCellDtoEvent event) {
        listener.acceptEvent(event);
    }
}
