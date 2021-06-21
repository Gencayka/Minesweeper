package ru.chayka.minesweeper.model.eventsystem.senders;

import ru.chayka.minesweeper.model.eventsystem.events.FirstCellOpeningEvent;
import ru.chayka.minesweeper.model.eventsystem.listeners.FirstCellOpeningEventListener;

public final class FirstCellOpeningEventSender
        extends MinesweeperModelEventSender<FirstCellOpeningEventListener, FirstCellOpeningEvent> {
    @Override
    protected void notifyOneListener(FirstCellOpeningEventListener listener, FirstCellOpeningEvent event) {
        listener.acceptEvent(event);
    }
}
