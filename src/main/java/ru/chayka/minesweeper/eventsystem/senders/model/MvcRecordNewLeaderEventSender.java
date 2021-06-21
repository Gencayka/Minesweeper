package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcRecordNewLeaderEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcRecordNewLeaderEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcRecordNewLeaderEventSender
        extends MvcEventSender<MvcRecordNewLeaderEventListener, MvcRecordNewLeaderEvent> {
    @Override
    protected void notifyOneListener(MvcRecordNewLeaderEventListener listener, MvcRecordNewLeaderEvent event) {
        listener.acceptEvent(event);
    }
}
