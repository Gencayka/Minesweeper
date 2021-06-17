package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MinefieldCellDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MinefieldCellDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class MinefieldCellDtoEventSender
        extends MinesweeperEventSender<MinefieldCellDtoEventListener, MinefieldCellDtoEvent> {
    @Override
    protected void notifyOneListener(MinefieldCellDtoEventListener listener, MinefieldCellDtoEvent event) {
        listener.acceptEvent(event);
    }
}
