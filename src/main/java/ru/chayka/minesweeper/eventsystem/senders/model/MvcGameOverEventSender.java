package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcGameOverEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcGameOverEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcGameOverEventSender
        extends MvcEventSender<MvcGameOverEventListener, MvcGameOverEvent> {
    @Override
    protected void notifyOneListener(MvcGameOverEventListener listener, MvcGameOverEvent event) {
        listener.acceptEvent(event);
    }
}
