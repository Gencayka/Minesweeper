package ru.chayka.minesweeper.eventsystem.senders.view;

import ru.chayka.minesweeper.eventsystem.events.view.MvcUnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MvcUnparameterizedButtonPressedEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcUnparameterizedButtonPressedEventSender
        extends MvcEventSender
        <MvcUnparameterizedButtonPressedEventListener,
                MvcUnparameterizedButtonPressedEvent> {
    @Override
    protected void notifyOneListener(
            MvcUnparameterizedButtonPressedEventListener listener,
            MvcUnparameterizedButtonPressedEvent event) {
        listener.acceptEvent(event);
    }
}
