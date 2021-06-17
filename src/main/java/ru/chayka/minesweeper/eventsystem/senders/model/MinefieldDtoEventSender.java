package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MinefieldDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MinefieldDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class MinefieldDtoEventSender
        extends MinesweeperEventSender<MinefieldDtoEventListener, MinefieldDtoEvent> {
    @Override
    protected void notifyOneListener(MinefieldDtoEventListener listener, MinefieldDtoEvent event) {
        listener.acceptEvent(event);
    }
}
