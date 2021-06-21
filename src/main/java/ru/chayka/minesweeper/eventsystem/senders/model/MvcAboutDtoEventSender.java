package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcAboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcAboutDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcAboutDtoEventSender
        extends MvcEventSender<MvcAboutDtoEventListener, MvcAboutDtoEvent> {
    @Override
    protected void notifyOneListener(MvcAboutDtoEventListener listener, MvcAboutDtoEvent event) {
        listener.acceptEvent(event);
    }
}
