package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcGameTimeDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcGameTimeDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcGameTimeDtoEventSender
        extends MvcEventSender<MvcGameTimeDtoEventListener, MvcGameTimeDtoEvent> {
    @Override
    protected void notifyOneListener(MvcGameTimeDtoEventListener listener, MvcGameTimeDtoEvent event) {
        listener.acceptEvent(event);
    }
}
