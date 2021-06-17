package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.RecordNewLeaderEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.RecordNewLeaderEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class RecordNewLeaderEventSender
        extends MinesweeperEventSender<RecordNewLeaderEventListener, RecordNewLeaderEvent> {
    @Override
    protected void notifyOneListener(RecordNewLeaderEventListener listener, RecordNewLeaderEvent event) {
        listener.acceptEvent(event);
    }
}
