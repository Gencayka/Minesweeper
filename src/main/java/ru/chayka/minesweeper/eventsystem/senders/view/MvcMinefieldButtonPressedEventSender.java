package ru.chayka.minesweeper.eventsystem.senders.view;

import ru.chayka.minesweeper.eventsystem.events.view.MvcMinefieldButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MvcMinefieldButtonPressedEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcMinefieldButtonPressedEventSender
        extends MvcEventSender<MvcMinefieldButtonPressedEventListener, MvcMinefieldButtonPressedEvent> {
    @Override
    protected void notifyOneListener(MvcMinefieldButtonPressedEventListener listener, MvcMinefieldButtonPressedEvent event) {
        listener.acceptEvent(event);
    }
}
